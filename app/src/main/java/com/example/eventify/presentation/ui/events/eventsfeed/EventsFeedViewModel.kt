package com.example.eventify.presentation.ui.events.eventsfeed

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.ImageLoader
import com.example.eventify.R
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.ui.SnackbarAction
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EventsFeedViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    @ApplicationContext private val context: Context,
    val imageLoader: ImageLoader,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )

    fun loadData() {
        viewModelScope.launch {
            loadEvents()
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                when (currentState) {
                    is UiState.Error -> currentState.copy(isRefreshing = true)
                    is UiState.ShowFeed -> currentState.copy(isRefreshing = true)
                    UiState.Loading -> return@launch
                }
            }
            loadEvents()
        }
    }

    private suspend fun loadEvents(){
        _stateFlow.update { _ ->
            when (val result = getEventsUseCase()){
                is Result.Error -> {
                    UiState.Error(
                        message = result.error.asUiText().asString(context)
                    )
                }
                is Result.Success -> {
                    UiState.ShowFeed(
                        events = result.data.map { it.toShortEventItem() }
                    )
                }
            }
        }
    }
}
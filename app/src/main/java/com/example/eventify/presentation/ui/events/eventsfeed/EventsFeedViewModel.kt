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

    private val _stateFlow: MutableStateFlow<EventsFeedState> = MutableStateFlow(EventsFeedState.default())
    val stateFlow: StateFlow<EventsFeedState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventsFeedState.default()
        )

    fun loadData() {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true) }
            loadEvents()
            _stateFlow.update { it.copy(isLoading = false) }

        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isRefreshing = true) }
            loadEvents()
            _stateFlow.update { it.copy(isRefreshing = false) }
        }
    }

    private suspend fun loadEvents(){
        when (val events = getEventsUseCase()){
            is Result.Error -> {
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = events.error.asUiText().asString(context),
                        duration = SnackbarDuration.Indefinite,
                        action = SnackbarAction(
                            name = context.getString(R.string.retry),
                            action = {
                                refreshData()
                            }
                        )
                    )
                )
            }
            is Result.Success -> {
                _stateFlow.update { currentState ->
                    currentState.copy(
                        events = events.data.map { it.toShortEventItem() }
                    )
                }
            }
        }
    }
}
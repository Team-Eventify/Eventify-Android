package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.ImageLoader
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val imageLoader: ImageLoader
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
                        message = events.error.toString()
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
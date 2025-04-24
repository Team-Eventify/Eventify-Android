package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.models.isHidden
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.ui.events.eventsfeed.state.UiState
import com.example.eventify.presentation.utils.BaseViewModel
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
) : BaseViewModel() {

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadEvents() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )


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

    private fun loadEvents(){
        launchCatching(
            catch = ::handleErrors
        ) {
            _stateFlow.update {
                UiState.ShowFeed(
                    events = getEventsUseCase()
                        .filter { !it.state.isHidden() }
                        .map {
                            it.toShortEventItem()
                        }
                )
            }
        }
    }

    private fun handleErrors(exception: Throwable) {
        _stateFlow.update {
            UiState.Error(
                message = exception.message
            )
        }
    }
}
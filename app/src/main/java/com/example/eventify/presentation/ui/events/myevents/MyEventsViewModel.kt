package com.example.eventify.presentation.ui.events.myevents

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetSubscribedEventsUseCase
import com.example.eventify.presentation.ui.events.myevents.state.UiState
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
import java.util.Date

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val getSubscribedEventsUseCase: GetSubscribedEventsUseCase,
) : BaseViewModel() {

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadEvents() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Initial
        )


    fun refresh(){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                when (currentState) {
                    is UiState.Empty -> currentState.copy(isRefreshing = true)
                    is UiState.Error -> currentState.copy(isRefreshing = true)
                    is UiState.ShowMyEvents -> currentState.copy(isRefreshing = true)
                    UiState.Initial -> return@launch
                }
            }
            loadEvents()
        }
    }

    private fun loadEvents(){
        launchCatching(
            catch = {
                // TODO обработать
            }
        ) {
            val currentDateTime = Date().time
            _stateFlow.update {
                getSubscribedEventsUseCase()
                    .map { it.toShortEventItem() }
                    .let { events ->
                        UiState.ShowMyEvents(
                            upComingEvents = events.filter { it.start >= currentDateTime },
                            finishedEvents = events.filter { it.end < currentDateTime }
                        )
                    }
            }
        }
    }
}
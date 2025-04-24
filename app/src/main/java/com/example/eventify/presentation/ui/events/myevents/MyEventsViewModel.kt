package com.example.eventify.presentation.ui.events.myevents

import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.models.isHidden
import com.example.eventify.domain.models.isSubscribeEnabled
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


@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val getSubscribedEventsUseCase: GetSubscribedEventsUseCase,
) : BaseViewModel() {

    private val mutableStateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val stateFlow: StateFlow<UiState> = mutableStateFlow
        .onStart { loadEvents() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Initial
        )


    fun refresh(){
        mutableStateFlow.update { currentState ->
            when (currentState) {
                is UiState.Empty -> currentState.copy(isRefreshing = true)
                is UiState.Error -> currentState.copy(isRefreshing = true)
                is UiState.ShowMyEvents -> currentState.copy(isRefreshing = true)
                UiState.Initial -> return
            }
        }
        loadEvents()

    }

    private fun loadEvents(){
        launchCatching(
            catch = { error ->
                mutableStateFlow.update { UiState.Error(message = error.message) }
            }
        ) {
            mutableStateFlow.update {
                getSubscribedEventsUseCase()
                    .map { it.toShortEventItem() }
                    .filter { !it.state.isHidden() }
                    .let { events ->
                        events.takeIf { it.isNotEmpty() }?.let {
                            UiState.ShowMyEvents(
                                upComingEvents = events.filter { it.state.isSubscribeEnabled() },
                                finishedEvents = events.filter { !it.state.isSubscribeEnabled() }
                            )
                        } ?: UiState.Empty()
                    }
            }
        }
    }
}
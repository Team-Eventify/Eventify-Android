package com.example.eventify.presentation.ui.eventdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import com.example.eventify.presentation.navgraphs.RootRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailUseCase: GetEventDetailUseCase,
) : ViewModel() {
    private val eventId = savedStateHandle.toRoute<RootRouter.EventDetailRoute>().eventId

    private val _stateFlow: MutableStateFlow<EventDetailState> =
        MutableStateFlow(EventDetailState())

    val stateFlow: StateFlow<EventDetailState> = _stateFlow.asStateFlow()

    init {
        loadEvent()
    }

    fun loadEvent(){
        viewModelScope.launch {
            val currentEvent = getEventDetailUseCase(eventId)

            _stateFlow.update { currentState ->
                currentState.copy(
                    event = currentEvent
                )
            }
        }
    }

}
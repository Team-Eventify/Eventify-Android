package com.example.eventify.presentation.ui.events.eventdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import com.example.eventify.domain.usecases.events.GetSubscribedEventsUseCase
import com.example.eventify.domain.usecases.events.SubscribeForEventUseCase
import com.example.eventify.domain.usecases.events.UnsubscribeForEventUseCase
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.security.PrivateKey

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailUseCase: GetEventDetailUseCase,
    private val subscribedEventsUseCase: SubscribeForEventUseCase,
    private val unsubscribeForEventUseCase: UnsubscribeForEventUseCase
) : ViewModel() {
    private val eventId = savedStateHandle.toRoute<RootRouter.EventDetailRoute>().eventId
    private val _stateFlow: MutableStateFlow<EventDetailState> =
        MutableStateFlow(EventDetailState())

    val stateFlow: StateFlow<EventDetailState> = _stateFlow
        .onStart { loadEvent() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventDetailState()
        )

    private fun loadEvent(){
        viewModelScope.launch {
            when (val currentEvent = getEventDetailUseCase(eventId)){
                is Result.Error -> TODO()
                is Result.Success -> {
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            event = currentEvent.data
                        )
                    }
                }
            }
        }
    }

    fun subscribeForEvent(){
//        viewModelScope.launch {
//            runCatching {
//                subscribedEventsUseCase(eventId)
//            }.onSuccess {
//                loadEvent()
//                SnackbarController.sendEvent(
//                    SnackbarEvent(
//                        message = "Вы подписались на событие"
//                    )
//                )
//            }.onFailure { exception ->
//                SnackbarController.sendEvent(
//                    SnackbarEvent(
//                        message = exception.message ?: "Ошибка"
//                    )
//                )
//            }
//        }

        viewModelScope.launch {
            when (val result = subscribedEventsUseCase(eventId)) {
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = result.error.toString())
                    )
                }
                is Result.Success -> {
                    loadEvent()
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = "Вы подписались на событие")
                    )
                }
            }
        }
    }

    fun unsubscribeForEvent(): Unit{
        viewModelScope.launch {
            runCatching {
                unsubscribeForEventUseCase(eventId)
            }.onSuccess {
                loadEvent()
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "Вы отписались от события"
                    )
                )
            }.onFailure { exception ->
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = exception.message ?: "Ошибка"
                    )
                )
            }
        }
    }

}
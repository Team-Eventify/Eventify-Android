package com.example.eventify.presentation.ui.events.eventdetail

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.data.exceptions.NetworkException
import com.example.eventify.data.exceptions.isNotFound
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import com.example.eventify.domain.usecases.events.SubscribeForEventUseCase
import com.example.eventify.domain.usecases.events.UnsubscribeForEventUseCase
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.ui.events.eventdetail.state.SideEffect
import com.example.eventify.presentation.ui.events.eventdetail.state.UiState
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailUseCase: GetEventDetailUseCase,
    private val subscribedEventsUseCase: SubscribeForEventUseCase,
    private val unsubscribeForEventUseCase: UnsubscribeForEventUseCase,
    @ApplicationContext private val context: Context,
) : BaseViewModel() {
    private val eventId by lazy {
        savedStateHandle.get<String>(ARG_EVENT_ID) ?: ""
    }

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadEvent() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )

    private fun loadEvent(){
        launchCatching(
            catch = ::handleEventErrors
        ) {
            _stateFlow.update {
                UiState.ShowEvent(
                    event = getEventDetailUseCase(eventId)
                )
            }
        }
    }

    fun subscribeForEvent(){
        launchCatching(
            catch = { error ->
                mutableSideEffect.trySend(SideEffect.FailSubscribeEvent(
                    error.message
                ))
            }
        ) {
            subscribedEventsUseCase(eventId)
            mutableSideEffect.send(SideEffect.SuccessSubscribeEvent)
        }
    }

    fun unsubscribeForEvent(){
        launchCatching(
            catch = { error ->
                mutableSideEffect.trySend(SideEffect.FailSubscribeEvent(
                    error.message
                ))
            }
        ) {
            unsubscribeForEventUseCase(eventId)
            mutableSideEffect.send(SideEffect.SuccessUnsubscribeEvent)
        }
    }

    private fun handleEventErrors(exception: Throwable) = when {
        exception.isNotFound() -> _stateFlow.update {
            UiState.Error(message = context.getString(R.string.not_found))
        }

        else -> _stateFlow.update {
            UiState.Error(message = context.getString(R.string.server_error))
        }

    }
}
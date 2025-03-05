package com.example.eventify.presentation.ui.events.eventdetail

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import com.example.eventify.domain.usecases.events.SubscribeForEventUseCase
import com.example.eventify.domain.usecases.events.UnsubscribeForEventUseCase
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.ui.events.eventdetail.state.SideEffect
import com.example.eventify.presentation.ui.events.eventdetail.state.UiState
import com.example.eventify.presentation.utils.asUiText
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
import kotlinx.coroutines.launch


@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailUseCase: GetEventDetailUseCase,
    private val subscribedEventsUseCase: SubscribeForEventUseCase,
    private val unsubscribeForEventUseCase: UnsubscribeForEventUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
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
        viewModelScope.launch {
            _stateFlow.update { _ ->
                when (val result = getEventDetailUseCase(eventId)){
                    is Result.Error -> UiState.Error(
                        message = result.error.asUiText().asString(context)
                    )
                    is Result.Success -> UiState.ShowEvent(
                        event = result.data
                    )
                }
            }
        }
    }

    fun subscribeForEvent(){
        viewModelScope.launch {
            when (val result = subscribedEventsUseCase(eventId)) {
                is Result.Error -> mutableSideEffect.send(SideEffect.FailSubscribeEvent(
                    result.error.asUiText().asString(context)
                ))
                is Result.Success -> mutableSideEffect.send(SideEffect.SuccessSubscribeEvent)
            }
        }
    }

    fun unsubscribeForEvent(){
        viewModelScope.launch {
            when (val result = unsubscribeForEventUseCase(eventId)){
                is Result.Error -> mutableSideEffect.send(SideEffect.FailUnsubscribeEvent(
                    result.error.asUiText().asString(context)
                ))
                is Result.Success -> {
                    mutableSideEffect.send(SideEffect.SuccessUnsubscribeEvent)
                }
            }
        }
    }
}
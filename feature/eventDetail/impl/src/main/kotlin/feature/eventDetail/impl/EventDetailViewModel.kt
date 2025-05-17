package feature.eventDetail.impl

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import core.common.navigation.ARG_EVENT_ID
import core.common.BaseViewModel
import core.common.extentions.isNotFound
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import domain.events.GetEventDetailUseCase
import domain.events.SubscribeForEventUseCase
import domain.events.UnsubscribeForEventUseCase
import domain.extentions.isSubscribeEnabled
import feature.eventDetail.impl.state.EventDetailUiState
import feature.eventDetail.impl.state.SideEffect
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.example.eventify.uikit.R



@HiltViewModel
internal class EventDetailViewModel @Inject constructor(
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

    private val _stateFlow: MutableStateFlow<EventDetailUiState> = MutableStateFlow(EventDetailUiState.Loading)
    val stateFlow: StateFlow<EventDetailUiState> = _stateFlow
        .onStart { loadEvent() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventDetailUiState.Loading
        )

    private fun loadEvent(isRefreshing: Boolean = false){
        launchCatching(
            catch = ::handleEventErrors
        ) {
            _stateFlow.update { currentState ->
                EventDetailUiState.Loading.takeUnless { isRefreshing } ?:
                (currentState as? EventDetailUiState.ShowEvent)?.copy(
                    isRefreshing = true,
                ) ?: currentState
            }

            _stateFlow.update {
                EventDetailUiState.ShowEvent(
                    event = getEventDetailUseCase(eventId),
                    isRefreshing = false,
                )
            }
        }
    }

    fun refresh() {
        loadEvent(isRefreshing = true)
    }

    fun primaryAction() {
        launchCatching {
            (_stateFlow.value as? EventDetailUiState.ShowEvent)?.event?.eventInfo?.let { event ->
                when {
                    // Subscribed and can be changed -> unsubscribe
                    event.state.isSubscribeEnabled() && event.subscribed -> {
                        unsubscribeForEvent()
                    }

                    // Unsubscribed and can be changed -> subscribe
                    event.state.isSubscribeEnabled() && event.subscribed.not() -> {
                        subscribeForEvent()
                    }
                    // Else ignore
                }
            }
        }
    }

    private fun subscribeForEvent(){
        launchCatching(
            catch = { error ->
                mutableSideEffect.trySend(SideEffect.FailSubscribeEvent(
                    error.message
                ))
            }
        ) {
            subscribedEventsUseCase(eventId)
            refresh()
            mutableSideEffect.send(SideEffect.SuccessSubscribeEvent)
        }
    }

    private fun unsubscribeForEvent(){
        launchCatching(
            catch = { error ->
                mutableSideEffect.trySend(SideEffect.FailSubscribeEvent(
                    error.message
                ))
            }
        ) {
            unsubscribeForEventUseCase(eventId)
            refresh()
            mutableSideEffect.send(SideEffect.SuccessUnsubscribeEvent)
        }
    }

    private fun handleEventErrors(exception: Throwable) = when {
        exception.isNotFound() -> _stateFlow.update {
            EventDetailUiState.Error(message = context.getString(R.string.not_found))
        }

        else -> _stateFlow.update {
            EventDetailUiState.Error(message = context.getString(R.string.server_error))
        }

    }
}
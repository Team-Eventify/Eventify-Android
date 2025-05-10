package feature.eventDetail.impl

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.data.exceptions.isNotFound
import com.example.eventify.domain.models.isSubscribeEnabled
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import com.example.eventify.domain.usecases.events.SubscribeForEventUseCase
import com.example.eventify.domain.usecases.events.UnsubscribeForEventUseCase
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.ui.events.eventdetail.state.SideEffect
import com.example.eventify.presentation.ui.events.eventdetail.state.EventDetailUiState
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
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

    private val _stateFlow: MutableStateFlow<EventDetailUiState> = MutableStateFlow(EventDetailUiState.Loading)
    val stateFlow: StateFlow<EventDetailUiState> = _stateFlow
        .onStart { loadEvent() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventDetailUiState.Loading
        )

    private fun loadEvent(){
        launchCatching(
            catch = ::handleEventErrors
        ) {
            _stateFlow.update {
                EventDetailUiState.ShowEvent(
                    event = getEventDetailUseCase(eventId)
                )
            }
        }
    }

    fun primaryAction() {
        launchCatching {
            _stateFlow
                .map { it as? EventDetailUiState.ShowEvent }
                .filterNotNull()
                .map { it.event.eventInfo }
                .collectLatest { event ->
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
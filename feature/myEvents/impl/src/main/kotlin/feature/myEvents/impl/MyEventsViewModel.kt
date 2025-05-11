package feature.myEvents.impl

import core.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.events.GetSubscribedEventsUseCase
import feature.myEvents.impl.state.UiState
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val getSubscribedEventsUseCase: GetSubscribedEventsUseCase,
) : BaseViewModel() {

    private val mutableStateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val stateFlow: StateFlow<UiState> = mutableStateFlow.asStateFlow()

    fun refresh(){
        mutableStateFlow.update { currentState ->
            when (currentState) {
                is UiState.Empty -> currentState.copy(isRefreshing = true)
                is UiState.Error -> currentState.copy(isRefreshing = true)
                is UiState.ShowMyEvents -> currentState.copy(isRefreshing = true)
                UiState.Initial -> currentState
            }
        }
        loadEvents()

    }

    fun loadEvents(){
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
package com.example.eventify.presentation.ui.events.eventdetail

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import coil3.ImageLoader
import com.example.eventify.R
import com.example.eventify.data.repositories.organizations.OrganizationsRepository
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import com.example.eventify.domain.usecases.events.SubscribeForEventUseCase
import com.example.eventify.domain.usecases.events.UnsubscribeForEventUseCase
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailUseCase: GetEventDetailUseCase,
    private val subscribedEventsUseCase: SubscribeForEventUseCase,
    private val unsubscribeForEventUseCase: UnsubscribeForEventUseCase,
    private val navigator: Navigator,
    val imageLoader: ImageLoader,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val eventId = savedStateHandle.toRoute<RootRouter.EventDetailRoute>().eventId
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
            when (val currentEvent = getEventDetailUseCase(eventId)){
                is Result.Error -> SnackbarController.sendEvent(
                    SnackbarEvent(message = currentEvent.error.asUiText().asString(context))
                )
                is Result.Success -> {
                    _stateFlow.update { _ ->
                        UiState.ShowEvent(
                            event = currentEvent.data
                        )
                    }
                }
            }
        }
    }

    fun subscribeForEvent(){
        viewModelScope.launch {
            when (val result = subscribedEventsUseCase(eventId)) {
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = result.error.asUiText().asString(context))
                    )
                }
                is Result.Success -> {
                    loadEvent()
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = context.getString(R.string.message_after_subscribe))
                    )
                    navigator.navigateUp()
                }
            }
        }
    }

    fun unsubscribeForEvent(){
        viewModelScope.launch {
            when (val result = unsubscribeForEventUseCase(eventId)){
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = result.error.asUiText().asString(context)
                        )
                    )
                }
                is Result.Success -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = context.getString(R.string.message_after_unsubscribe)
                        )
                    )
                    navigator.navigateUp()
                }
            }
        }
    }

    fun navigateToRate() {
        viewModelScope.launch {
            navigator.navigate(RootRouter.EventFeedbackRoute(eventId = eventId))
        }
    }

}
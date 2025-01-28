package com.example.eventify.presentation.ui.events.myevents

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetSubscribedEventsUseCase
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarAction
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
import java.util.Date

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val getSubscribedEventsUseCase: GetSubscribedEventsUseCase,
    private val navigator: Navigator,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Initial
        )


    fun loadData(){
        viewModelScope.launch {
            loadEvents()
        }
    }

    fun refresh(){
        viewModelScope.launch {
            loadEvents()
        }
    }

    private suspend fun loadEvents(){
        val currentDateTime = Date().time

        val events = when (val result = getSubscribedEventsUseCase()){
            is Result.Error -> {
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = result.error.asUiText().asString(context),
                        duration = SnackbarDuration.Indefinite,
                        action = SnackbarAction(
                            name = context.getString(R.string.retry),
                            action = {
                                refresh()
                            }
                        )
                    )
                )
                return
            }
            is Result.Success -> result.data.map { it.toShortEventItem() }
        }

        _stateFlow.update { _ ->
            if (events.isEmpty()) {
                UiState.Empty
            } else {
                UiState.ShowMyEvents(
                    upComingEvents = events.filter { it.start >= currentDateTime },
                    finishedEvents = events.filter { it.end < currentDateTime }
                )
            }
        }
    }


    fun navigateToEvent(eventId: String) {
        viewModelScope.launch {
            navigator.navigate(RootRouter.EventDetailRoute(eventId))
        }
    }

    fun navigateToFeedback(eventId: String){
        viewModelScope.launch {
            navigator.navigate(RootRouter.EventFeedbackRoute(eventId = eventId))
        }
    }

}
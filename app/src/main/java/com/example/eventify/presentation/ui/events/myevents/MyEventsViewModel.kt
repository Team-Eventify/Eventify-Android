package com.example.eventify.presentation.ui.events.myevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.events.GetSubscribedEventsUseCase
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditState
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
import timber.log.Timber
import java.lang.Exception
import java.time.LocalDate
import java.util.Date

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val getSubscribedEventsUseCase: GetSubscribedEventsUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MyEventsState> = MutableStateFlow(MyEventsState.default())
    val stateFlow: StateFlow<MyEventsState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            MyEventsState.default()
        )


    fun loadData(){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    isRefreshing = true
                )
            }
            val currentDateTime = Date().time

            when (val result = getSubscribedEventsUseCase()){
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = result.error.toString())
                    )
                }
                is Result.Success -> {
                    _stateFlow.update { currentState ->
                        val events = result.data.map {
                            ShortEventItem(
                                id = it.id,
                                title = it.title,
                                description = it.description,
                                start = it.start,
                                end = it.end
                            )
                        }
                        currentState.copy(
                            upComingEvents = events.filter { it.start >= currentDateTime },
                            finishedEvents = events.filter { it.end < currentDateTime }
                        )
                    }
                }
            }


            _stateFlow.update { currentState ->
                currentState.copy(
                    isRefreshing = false
                )
            }
        }
    }

    fun refresh(){
        loadData()
    }

    fun navigateToEvent(eventId: String) {
        viewModelScope.launch {
            navigator.navigate(RootRouter.EventDetailRoute(eventId))
        }
    }

}
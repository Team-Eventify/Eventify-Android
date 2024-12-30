package com.example.eventify.presentation.ui.events.myevents

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.events.GetSubscribedEventsUseCase
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditState
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val navigator: Navigator,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MyEventsState> = MutableStateFlow(MyEventsState(isLoading = true))
    val stateFlow: StateFlow<MyEventsState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            MyEventsState(isLoading = true)
        )


    fun loadData(){
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true) }
            loadEvents()
            _stateFlow.update { it.copy(isLoading = false) }
        }
    }

    fun refresh(){
        viewModelScope.launch {
            _stateFlow.update { it.copy(isRefreshing = true) }
            loadEvents()
            _stateFlow.update { it.copy(isRefreshing = false) }
        }
    }

    private suspend fun loadEvents(){
        val currentDateTime = Date().time

        val eventsInfo = when (val result = getSubscribedEventsUseCase()){
            is Result.Error -> {
                when (result.error){
                    is DataError.Network -> {
                        when (result.error){
                            DataError.Network.NOT_FOUND -> emptyList()
                            else -> return handleError(result.error)
                        }
                    }
                    else -> return handleError(result.error)
                }
            }
            is Result.Success -> result.data
        }

        _stateFlow.update { currentState ->
            val events = eventsInfo.map {
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

    private suspend fun handleError(error: DataError){
        SnackbarController.sendEvent(
            SnackbarEvent(message = error.asUiText().asString(context))
        )
    }

    fun navigateToEvent(eventId: String) {
        viewModelScope.launch {
            navigator.navigate(RootRouter.EventDetailRoute(eventId))
        }
    }

}
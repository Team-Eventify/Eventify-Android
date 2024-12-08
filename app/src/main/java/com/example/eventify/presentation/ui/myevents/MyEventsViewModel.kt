package com.example.eventify.presentation.ui.myevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.events.GetSubscribedEventsUseCase
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.ui.profileedit.ProfileEditState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val getSubscribedEventsUseCase: GetSubscribedEventsUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MyEventsState> = MutableStateFlow(MyEventsState.default())
    val stateFlow: StateFlow<MyEventsState> = _stateFlow.asStateFlow()

    private val _currentUser: MutableStateFlow<UserInfo?> = MutableStateFlow(null)
    val currentUser: StateFlow<UserInfo?> = _currentUser.asStateFlow()

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    isRefreshing = true
                )
            }

            runCatching {
                loadSubscribedEvents()
            }.onSuccess {

            }.onFailure { exception ->
                Timber.e(exception)
                handleErrors(exception)
                SnackbarController.sendEvent(SnackbarEvent(
                    message = "Cant load events ${exception.message}"
                ))
            }
            _stateFlow.update { currentState ->
                currentState.copy(
                    isRefreshing = false
                )
            }
        }
    }

    private suspend fun loadSubscribedEvents(){
        // TODO refactor this block

        _currentUser.value = getCurrentUserUseCase()
        _stateFlow.update { currentState ->
            currentUser.value?.run {
                currentState.copy(
                    upComingEvents = getSubscribedEventsUseCase(id).map {
                        ShortEventItem(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            start = it.start,
                            end = it.end
                        )
                    }
                )
            } ?: MyEventsState.default()
        }
    }

    private fun handleErrors(exception: Throwable){
        // TODO handle errors
    }

    fun refresh(){
        loadData()
    }

}
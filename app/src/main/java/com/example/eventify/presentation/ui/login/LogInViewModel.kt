package com.example.eventify.presentation.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.exceptions.UserNotFoundException
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.domain.usecases.LoginUseCase
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LogInState> = MutableStateFlow(LogInState.default())
    val stateFlow: StateFlow<LogInState> = _stateFlow.asStateFlow()

    fun changeLogin(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value,
            )
        }
    }

    fun changePassword(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value,
            )
        }
    }

    fun logIn(){
        viewModelScope.launch {
            try {
                logInUser()
            } catch (e: UserNotFoundException){

            }
        }
    }

    private fun validateFormData(): Boolean{
        return _stateFlow.value.run { isValidLogin && isValidPassword  }
    }

    private suspend fun logInUser(){
        val userCredentials = stateFlow.value.run {
            UserCredentials(
                login = login,
                password = password
            )
        }
        try {
            loginUseCase(credentials = userCredentials)
        } catch (e: Exception){
            // TODO write handlers
            return
        }

        navigator.navigate(RootRouter.HomeRoute)
    }

}
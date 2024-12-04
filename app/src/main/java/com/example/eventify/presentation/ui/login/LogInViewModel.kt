package com.example.eventify.presentation.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.exceptions.UserNotFoundException
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.domain.usecases.LoginUseCase
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
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
        if (!validateFormData()) return

        val userCredentials = stateFlow.value.run {
            UserCredentials(
                login = login,
                password = password
            )
        }
        logInUser(credentials = userCredentials)
    }

    private fun logInUser(credentials: UserCredentials){
        viewModelScope.launch {
            runCatching {
                loginUseCase(credentials = credentials)
            }.onSuccess {
                navigator.navigate(RootRouter.HomeRoute)
            }.onFailure { exception ->
                handleErrors(exception)
            }
        }
    }

    private suspend fun handleErrors(exception: Throwable){
        // TODO handle detail errors
        _stateFlow.update { currentState ->
            when (exception){
                is UserNotFoundException -> {
                    SnackbarController.sendEvent(SnackbarEvent(message = exception.message!!))
                    currentState.copy(
                        hasLoginError = true,
                        hasPasswordError = true
                    )
                }
                else -> {
                    SnackbarController.sendEvent(SnackbarEvent(message = exception.message ?: "Ошибка сервера"))
                    currentState.copy(
                        hasLoginError = true,
                        hasPasswordError = true
                    )
                }
            }
        }

    }

    private fun validateFormData(): Boolean{
        return _stateFlow.value.run { isValidLogin && isValidPassword  }
    }

    fun navigateToRegister(){
        viewModelScope.launch {
            navigator.navigate(AuthRouter.RegisterRoute)
        }
    }

}
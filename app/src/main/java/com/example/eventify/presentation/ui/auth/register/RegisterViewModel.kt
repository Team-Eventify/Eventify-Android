package com.example.eventify.presentation.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserCreate
import com.example.eventify.domain.usecases.account.RegisterUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidatePassword
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
import timber.log.Timber

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val validateEmail: ValidateEmail = ValidateEmail()
    private val validatePassword: ValidatePassword = ValidatePassword()

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.default())
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()


    fun changeLogin(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value
            )
        }
    }

    fun changePassword(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value
            )
        }
    }

    private fun validateForm(): Boolean{
        val isValidEmail = validateEmail(_stateFlow.value.login)
        val isValidPassword = validatePassword(_stateFlow.value.password)

        val hasError = listOf(
            isValidEmail,
            isValidPassword
        ).any { !it.successful }

        if (hasError){
            _stateFlow.update { currentState ->
                currentState.copy(
                    hasLoginError = !isValidEmail.successful,
                    loginError = isValidEmail.errorMessage,
                    hasPasswordError = !isValidPassword.successful,
                    passwordError = isValidPassword.errorMessage
                )
            }
        }

        return hasError
    }


    fun register(){
        if (validateForm()) return

        val userPayload = stateFlow.value.run {
            UserCreate(
                email = login,
                password = password
            )
        }
        registerUser(payload = userPayload)
    }

    private fun registerUser(payload: UserCreate){
        viewModelScope.launch {
            runCatching {
                registerUseCase(user = payload)
            }.onSuccess {
                navigator.navigate(RootRouter.HomeRoute)
            }.onFailure { exception ->
                handleErrors(exception)
            }
        }
    }

    private suspend fun handleErrors(exception: Throwable){
        Timber.e(exception)
        // TODO handle detail errors
        _stateFlow.update { currentState ->
            when (exception){
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

    fun navigateToLogin(){
        viewModelScope.launch {
            navigator.navigate(AuthRouter.LogInRoute)
        }
    }

}
package com.example.eventify.presentation.ui.auth.login

import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.domain.usecases.auth.LoginUseCase
import com.example.eventify.presentation.ui.auth.login.state.LogInState
import com.example.eventify.presentation.ui.auth.login.state.SideEffect
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<LogInState> = MutableStateFlow(LogInState.default())
    val stateFlow: StateFlow<LogInState> = _stateFlow.asStateFlow()

    fun changeLogin(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    fun changePassword(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    fun logIn() {
        launchCatching(
            catch = {
                // TODO обработать
            }
        ) {
            if (!validateFormData()) return@launchCatching

            val userCredentials = stateFlow.value.run {
                UserCredentials(
                    login = login,
                    password = password
                )
            }

            loginUseCase(credentials = userCredentials)
            mutableSideEffect.send(SideEffect.SuccessLogIn)
        }

    }

    private fun handleErrors(error: Throwable){
        // TODO обработать
    }

    private fun validateFormData(): Boolean{
        return _stateFlow.value.run { isValidLogin && isValidPassword  }
    }
}
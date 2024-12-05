package com.example.eventify.presentation.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserCreate
import com.example.eventify.domain.usecases.account.RegisterUseCase
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
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val navigator: Navigator
) : ViewModel() {

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


    fun register(){
        if (!stateFlow.value.isValidFormData) return

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
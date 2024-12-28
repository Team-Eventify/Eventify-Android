package com.example.eventify.presentation.ui.auth.login

import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.eventify.data.exceptions.UserNotFoundException
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.LoginUseCase
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

        viewModelScope.launch {
            when (val result = loginUseCase(credentials = userCredentials)){
                is Result.Error -> handleErrors(result.error)
                is Result.Success -> {
                    navigator.navigate(
                        RootRouter.HomeRoute
                    ) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    private suspend fun handleErrors(error: DataError){
        when (error){
            is DataError.API -> {
                when (error){
                    DataError.API.NOT_FOUND -> {
                        SnackbarController.sendEvent(
                            SnackbarEvent(message = error.toString())
                        )
                    }
                    DataError.API.BAD_REQUEST -> TODO()
                    DataError.API.FORBIDDEN -> TODO()
                }
            }
            is DataError.Network -> {
                SnackbarController.sendEvent(
                    SnackbarEvent(message = error.toString())
                )
            }
            else -> {}
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

    fun navigateToResetPassword(){
        viewModelScope.launch {
            navigator.navigate(AuthRouter.ResetPasswordRoute(stateFlow.value.login))
        }
    }

}
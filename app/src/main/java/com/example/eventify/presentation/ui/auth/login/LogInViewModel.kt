package com.example.eventify.presentation.ui.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.LoginUseCase
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator,
    @ApplicationContext private val context: Context
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
            is DataError.Network -> {
                when (error){
                    DataError.Network.NOT_FOUND -> SnackbarController.sendEvent(
                        SnackbarEvent(message = context.getString(R.string.user_not_found))
                    )
                    else -> SnackbarController.sendEvent(
                        SnackbarEvent(message = error.asUiText().asString(context = context))
                    )
                }
            }
            else -> SnackbarController.sendEvent(
                SnackbarEvent(message = error.asUiText().asString(context = context))
            )
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
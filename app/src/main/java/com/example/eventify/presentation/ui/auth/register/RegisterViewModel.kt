package com.example.eventify.presentation.ui.auth.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserCreate
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.RegisterUseCase
import com.example.eventify.domain.validation.EmailValidationError
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidatePassword
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
import timber.log.Timber

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val navigator: Navigator,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val validateEmailUseCase: ValidateEmail = ValidateEmail()
    private val validatePasswordUseCase: ValidatePassword = ValidatePassword()

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.default())
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()


    fun changeLogin(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value
            )
        }
    }

    private fun validateLogin(): Boolean{
        val validationResult = validateEmailUseCase(_stateFlow.value.login)
        val (error, hasError) = when (validationResult){
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            currentState.copy(
                loginError = error,
                hasLoginError = hasError
            )
        }
        return validationResult is Result.Success
    }

    fun changePassword(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value
            )
        }
    }

    private fun validatePassword(): Boolean{
        val validationResult = validatePasswordUseCase(_stateFlow.value.password)
        val (error, hasError) = when (validationResult){
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            currentState.copy(
                passwordError = error,
                hasPasswordError = hasError
            )
        }
        return validationResult is Result.Success
    }


    fun register(){
        val isValidData = listOf(
            validateLogin(),
            validatePassword()
        ).all{ it }

        if (!isValidData) return


        val userPayload = stateFlow.value.run {
            UserCreate(
                email = login,
                password = password
            )
        }

        viewModelScope.launch {
            when (val result = registerUseCase(user = userPayload)){
                is Result.Error -> handleErrors(result.error)
                is Result.Success -> {
                    navigator.navigate(AuthRouter.ChooseCategoriesRoute){
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
            is DataError.API -> {}
            else -> {
                SnackbarController.sendEvent(
                    SnackbarEvent(message = error.asUiText().asString(context))
                )
            }
        }
    }

    fun navigateToLogin(){
        viewModelScope.launch {
            navigator.navigate(AuthRouter.LogInRoute)
        }
    }

}
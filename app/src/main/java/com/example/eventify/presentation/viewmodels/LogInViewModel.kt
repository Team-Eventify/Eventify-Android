package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.exceptions.UserNotFoundException
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.domain.usecases.LoginUseCase
import com.example.eventify.presentation.models.LogInUiState
import com.example.eventify.presentation.models.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(LogInUiState.default())
        private set

    var loginResult by mutableStateOf<LoginResult>(LoginResult.Idle)
        private set

    fun changeLoginValue(value: String) {
        uiState = uiState.copy(
            loginValue = value
        )
    }

    fun changePasswordValue(value: String) {
        uiState = uiState.copy(
            passwordValue = value
        )
    }

    fun logIn(): Unit {
        if (!uiState.isValidData){
            loginResult = LoginResult.Error(message = "Заполните данные")
            uiState = uiState.copy(
                hasErrors = true,
                errorMessage = "Заполните данные"
            )
            return
        }

        viewModelScope.launch {
            loginResult = LoginResult.Loading

            val credentials = UserCredentials(
                login = uiState.loginValue,
                password = uiState.passwordValue
            )

            try {
                loginUseCase(credentials)
                loginResult = LoginResult.Success

            } catch(e: UserNotFoundException) {
                val errorMessage = e.message ?: "Пользователь не найден."
                loginResult = LoginResult.Error(message = errorMessage)
                uiState = uiState.copy(
                    hasErrors = true,
                    errorMessage = errorMessage
                )

            } catch (e: Exception) {
                val errorMessage = e.message ?: "Ошибка сервера."
                loginResult = LoginResult.Error(message = errorMessage)
                uiState = uiState.copy(
                    hasErrors = true,
                    errorMessage = errorMessage
                )
            }
        }
    }

}

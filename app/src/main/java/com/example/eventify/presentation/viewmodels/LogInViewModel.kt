package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.remote.utils.TokenManager
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.repositories.AuthUserRepository
import com.example.eventify.presentation.models.LogInUiState
import com.example.eventify.presentation.models.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenManager: TokenManager
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

    fun logIn(

    ) = viewModelScope.launch {
        if (!(uiState.loginValue.isNotEmpty() && uiState.passwordValue.isNotEmpty())){
            loginResult = LoginResult.Error(message = "Заполните данные")
            uiState = uiState.copy(
                hasErrors = true,
                errorMessage = "Заполните данные"
            )
            return@launch
        }

        loginResult = LoginResult.Loading

        val response = authRepository.logInUser(
            LogInRequestData(
                email = uiState.loginValue,
                password = uiState.passwordValue
            )
        )
        when (response.code()){
            200 -> {
                response.body()?.let {
                    tokenManager.setRefreshToken(it.refreshToken)
                    tokenManager.setAccessToken(it.accessToken)
                }
                loginResult = LoginResult.Success
            }
            404 -> {
                loginResult = LoginResult.Error(message = "Пользователь не найден")
                uiState = uiState.copy(
                    hasErrors = true,
                    errorMessage = "Пользователь не найден"
                )
            }
            else -> {
                loginResult = LoginResult.Error(message = "Ошибка сервера")
                uiState = uiState.copy(
                    hasErrors = true,
                    errorMessage = "Ошибка сервера"
                )
            }
        }
    }

}

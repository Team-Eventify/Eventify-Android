package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserCreate
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.usecases.RegisterUseCase
import com.example.eventify.presentation.models.RegisterResult
import com.example.eventify.presentation.models.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    var uiState by mutableStateOf(RegisterUiState.default())
        private set

    var registerResult by mutableStateOf<RegisterResult>(RegisterResult.Idle)
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

    fun register() {
        if (!uiState.isValidData) {
            uiState = uiState.copy(
                errorMessage = "Заполните данные",
                hasErrors = true
            )
            return
        }

        val newUserData = UserCreate(
            email = uiState.loginValue,
            password = uiState.passwordValue
        )

        viewModelScope.launch {
            registerResult = try {
                registerUseCase(user = newUserData)
                RegisterResult.Success

            } catch (e: Exception) {
                RegisterResult.Error(message = "Ошибка")
            }
        }
    }
}
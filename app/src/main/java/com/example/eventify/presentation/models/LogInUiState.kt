package com.example.eventify.presentation.models

sealed class LoginResult {
    object Idle : LoginResult()
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String): LoginResult()
}


data class LogInUiState(
    val loginValue: String,
    val passwordValue: String,
    val hasErrors: Boolean,
    val errorMessage: String?,
    val isValidForm: Boolean
){
    val isValidData: Boolean
        get() = loginValue.isNotEmpty() && passwordValue.isNotEmpty()

    companion object {
        fun default(): LogInUiState = LogInUiState(
            loginValue = "",
            passwordValue = "",
            hasErrors = false,
            errorMessage = null,
            isValidForm = false
        )
    }
}

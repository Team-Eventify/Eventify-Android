package com.example.eventify.presentation.models


sealed class RegisterResult {
    object Idle : RegisterResult()
    object Loading : RegisterResult()
    object Success : RegisterResult()
    data class Error(val message: String): RegisterResult()
}


data class RegisterUiState(
    val loginValue: String,
    val loginError: String?,
    val passwordValue: String,
    val passwordError: String?,
    val hasErrors: Boolean,
    val errorMessage: String?
){
    val isValidData: Boolean
        get() = loginValue.isNotEmpty() && passwordValue.isNotEmpty()

    companion object {
        fun default() = RegisterUiState(
            loginValue = "",
            loginError = null,
            passwordValue = "",
            passwordError = null,
            hasErrors = false,
            errorMessage = null
        )
    }
}



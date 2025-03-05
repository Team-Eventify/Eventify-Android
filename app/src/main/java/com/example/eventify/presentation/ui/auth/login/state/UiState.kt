package com.example.eventify.presentation.ui.auth.login.state

data class LogInState(
    val login: String,
    val hasLoginError: Boolean = false,
    val loginError: String?,

    val password: String,
    val hasPasswordError: Boolean = false,
    val passwordError: String?,

    ){
    val isValidLogin: Boolean
        get() = login.isNotEmpty()

    val isValidPassword: Boolean
        get() = password.isNotEmpty()

    val isValidForm: Boolean
        get() = isValidLogin && isValidPassword

    companion object {
        fun default() = LogInState(
            login = "",
            loginError = null,
            password = "",
            passwordError = null,
        )
    }
}
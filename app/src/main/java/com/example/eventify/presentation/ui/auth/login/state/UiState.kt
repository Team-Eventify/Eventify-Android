package com.example.eventify.presentation.ui.auth.login.state

data class LogInState(
    val login: String = "",
    val hasLoginError: Boolean = false,
    val loginError: String? = null,

    val password: String = "",
    val hasPasswordError: Boolean = false,
    val passwordError: String? = null,

    ){
    val isValidLogin: Boolean
        get() = login.isNotEmpty()

    val isValidPassword: Boolean
        get() = password.isNotEmpty()

    val isValidForm: Boolean
        get() = isValidLogin && isValidPassword
}
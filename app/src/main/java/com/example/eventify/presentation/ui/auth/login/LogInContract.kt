package com.example.eventify.presentation.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents LogInScreen
 **/
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

/**
 * LogIn Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LogInActions(
    val onChangeLogin: (String) -> Unit = {},
    val onChangePassword: (String) -> Unit = {},
    val onSubmit: () -> Unit = {},
    val navigateToRegister: () -> Unit = {},
    val navigateToResetPassword: () -> Unit = {},
)
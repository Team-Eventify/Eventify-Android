package com.example.eventify.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents LogInScreen
 **/
data class LogInState(
    val login: String,
    val isValidLogin: Boolean,
    val loginError: String?,

    val password: String,
    val isValidPassword: Boolean,
    val passwordError: String?,

    val isValidForm: Boolean
){
    companion object {
        fun default() = LogInState(
            login = "",
            isValidLogin = false,
            loginError = null,
            password = "",
            isValidPassword = false,
            passwordError = null,
            isValidForm = false
        )
    }
}

/**
 * LogIn Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LogInActions(
    val onChangeLogin: (String) -> Unit,
    val onChangePassword: (String) -> Unit,
    val onSubmit: () -> Unit,
    val navigateToRegister: () -> Unit,
)
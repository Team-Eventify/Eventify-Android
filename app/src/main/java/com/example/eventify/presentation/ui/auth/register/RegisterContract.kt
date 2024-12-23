package com.example.eventify.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents RegisterScreen
 **/
data class RegisterState(
    val login: String,
    val hasLoginError: Boolean = false,
    val loginError: String? = null,

    val password: String,
    val hasPasswordError: Boolean = false,
    val passwordError: String? = null
){
    val isValidLogin: Boolean
        get() = login.isNotEmpty()

    val isValidPassword: Boolean
        get() = password.isNotEmpty()

    val isValidFormData: Boolean
        get() = isValidLogin && isValidPassword

    companion object {
        fun default() = RegisterState(
            login = "",
            password = ""
        )
    }
}

/**
 * Register Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class RegisterActions(
    val navigateToLogIn: () -> Unit,
    val onChangeLogin: (String) -> Unit,
    val onChangePassword: (String) -> Unit,
    val onSubmit: () -> Unit,
)
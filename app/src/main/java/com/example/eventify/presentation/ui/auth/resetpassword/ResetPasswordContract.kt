package com.example.eventify.presentation.ui.auth.resetpassword


/**
 * UI State that represents ResetPasswordScreen
 **/
data class ResetPasswordState(
    val email: String = ""
){
    val isValidEmail: Boolean
        get() = email.isNotEmpty() && email.isNotBlank()
}

/**
 * ResetPassword Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ResetPasswordActions(
    val onSubmit: () -> Unit = {},
    val onChangeEmail: (String) -> Unit = {}
)
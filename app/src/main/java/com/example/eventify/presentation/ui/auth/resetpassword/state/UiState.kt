package com.example.eventify.presentation.ui.auth.resetpassword.state

data class UiState(
    val email: String = ""
) {
    val isValidEmail: Boolean
        get() = email.isNotEmpty() && email.isNotBlank()
}

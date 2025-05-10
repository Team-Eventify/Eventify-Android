package feature.resetPassword.impl.state

data class UiState(
    val email: String = ""
) {
    val isValidEmail: Boolean
        get() = email.isNotEmpty() && email.isNotBlank()
}

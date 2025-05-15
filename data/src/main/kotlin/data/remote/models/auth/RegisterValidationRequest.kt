package data.remote.models.auth

internal data class RegisterValidationRequest(
    val email: String,
    val password: String
)
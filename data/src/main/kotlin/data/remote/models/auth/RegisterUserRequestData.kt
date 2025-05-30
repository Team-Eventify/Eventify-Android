package data.remote.models.auth

internal data class RegisterUserRequestData(
    val email: String,
    val password: String
)
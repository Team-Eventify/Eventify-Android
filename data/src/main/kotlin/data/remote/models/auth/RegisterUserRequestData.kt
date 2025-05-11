package data.remote.models.auth

data class RegisterUserRequestData(
    val email: String,
    val password: String
)
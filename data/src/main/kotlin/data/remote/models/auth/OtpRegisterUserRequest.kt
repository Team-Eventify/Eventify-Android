package data.remote.models.auth

internal data class OtpRegisterUserRequest(
    val email: String,
    val password: String,
    val code: String
)
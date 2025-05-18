package data.remote.models.auth

internal data class LogInRequestData(
    val email: String,
    val password: String
)
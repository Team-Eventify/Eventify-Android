package data.remote.models.auth

internal data class LogInResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
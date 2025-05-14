package data.remote.models.auth

data class LogInResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
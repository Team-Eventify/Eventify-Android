package data.remote.models.auth

internal data class RegisterResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
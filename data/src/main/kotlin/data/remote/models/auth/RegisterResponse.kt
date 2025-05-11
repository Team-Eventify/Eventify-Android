package data.remote.models.auth

data class RegisterResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
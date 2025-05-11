package data.remote.models.auth

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
package data.remote.models.auth

internal data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
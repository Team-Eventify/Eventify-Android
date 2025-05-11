package data.models

data class TokenData(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)

package data.models

import data.remote.models.auth.RegisterResponse
import data.remote.models.auth.LogInResponse
import data.remote.models.auth.RefreshTokenResponse


data class TokenData(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)

internal fun RegisterResponse.toTokenData() = TokenData(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken,
    userID = this.userID,
)

internal fun LogInResponse.toTokenData() = TokenData(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken,
    userID = this.userID,
)

internal fun RefreshTokenResponse.toTokenData() = TokenData(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken,
    userID = this.userID,
)

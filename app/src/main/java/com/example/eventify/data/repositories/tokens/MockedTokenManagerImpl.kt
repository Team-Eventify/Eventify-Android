package com.example.eventify.data.repositories.tokens

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT

class MockedTokenManagerImpl: TokenManager {
    private var accessToken: String? = null
    private var refreshToken: String? = null
    private var userId: String? = null
    override fun getAccessToken(): String? = accessToken

    override fun setAccessToken(token: String) {
        accessToken = token
    }

    override fun getRefreshToken(): String? = refreshToken

    override fun setRefreshToken(token: String) {
        refreshToken = token
    }

    override fun getUserId(): String? = userId

    override fun setUserId(value: String) {
        userId = value
    }

    override fun clear() {
        accessToken = null
        refreshToken = null
        userId = null
    }

    override fun isValidData(): Boolean {
        val accessToken = getAccessToken()?.let { decodeToken(it) } ?: return false
        val refreshToken = getRefreshToken()?.let { decodeToken(it) } ?: return false
        getUserId() ?: return false

        val currentDate = System.currentTimeMillis()

        return !(accessToken.expiresAt.time < currentDate && refreshToken.expiresAt.time < currentDate)
    }
    private fun decodeToken(token: String): DecodedJWT? {
        return try {
            JWT.decode(token)
        } catch (e: Exception){
            // TODO write logs
            null
        }
    }
}
package com.example.eventify.data.repositories.tokens

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
}
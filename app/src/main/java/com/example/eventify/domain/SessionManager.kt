package com.example.eventify.domain

import com.example.eventify.data.repositories.tokens.TokenManager
import javax.inject.Inject

interface SessionManager {
    fun isLoggedIn(): Boolean
}

class SessionManagerImpl @Inject constructor(
    private val tokenManager: TokenManager
) : SessionManager {
    override fun isLoggedIn(): Boolean = tokenManager.isValidData()


}
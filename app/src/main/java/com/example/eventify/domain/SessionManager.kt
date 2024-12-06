package com.example.eventify.domain

import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

interface SessionManager {
    suspend fun isLoggedIn(): Boolean
}

class SessionManagerImpl @Inject constructor(
    private val tokenManager: TokenManager
) : SessionManager {
    override suspend fun isLoggedIn(): Boolean = tokenManager.isValidData()
}

class SessionManagerRequestsImpl @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenManager: TokenManager
) : SessionManager{
    override suspend fun isLoggedIn(): Boolean {
        if (!tokenManager.isValidData()) return false
        val userId = tokenManager.getUserId()

        return try {
            val user = userId?.let { usersRepository.getUserInfo(userId = it) }
            user != null
        } catch (e: Exception){
            false
        }
    }

}
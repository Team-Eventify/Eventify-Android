package com.example.eventify.domain

import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import timber.log.Timber
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
        try {
            if (!tokenManager.isValidData()) return false
            val userId = tokenManager.getUserId() ?: return false

            return when (usersRepository.getUserInfo(userId)){
                is Result.Error -> false
                is Result.Success -> true
            }

        } catch (e: Exception){
            Timber.e(e)
            return false
        }
    }

}
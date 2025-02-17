package com.example.eventify.domain

import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.users.UsersRepository
import timber.log.Timber
import javax.inject.Inject

interface SessionManager {
    suspend fun isLoggedIn(): Boolean
}

class SessionManagerImpl @Inject constructor(
    private val tokenProvider: TokenProvider
) : SessionManager {
    override suspend fun isLoggedIn(): Boolean = tokenProvider.isValidData()
}

class SessionManagerRequestsImpl @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) : SessionManager{

    override suspend fun isLoggedIn(): Boolean {
        try {
            if (!tokenProvider.isValidData()) return false
            val userId = tokenProvider.getUserId() ?: return false

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
package domain

import core.common.secure.tokens.TokenProvider
import data.repositories.users.UsersRepository
import javax.inject.Inject

interface SessionManager {
    suspend fun isLoggedIn(): Boolean
}

class SessionManagerRequestsImpl @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) : SessionManager{

    override suspend fun isLoggedIn(): Boolean {
        try {
            if (!tokenProvider.isValidData()) return false
            val userId = tokenProvider.getUserId() ?: return false
            usersRepository.getUserInfo(userId)
            return true
        } catch (e: Exception){
            return false
        }
    }

}
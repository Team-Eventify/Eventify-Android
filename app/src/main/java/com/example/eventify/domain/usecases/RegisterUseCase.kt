package com.example.eventify.domain.usecases

import com.example.eventify.data.models.UserCreate
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenManager: TokenManager
) {
    suspend fun register(user: UserCreate): Unit {
        val tokenData = authRepository.registerUser(user = user)

        tokenManager.apply {
            setAccessToken(tokenData.accessToken)
            setRefreshToken(tokenData.refreshToken)
        }
    }
}
package com.example.eventify.domain.usecases

import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.auth.AuthUserRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthUserRepository
) {
    suspend operator fun invoke(credentials: UserCredentials) {
        val tokenData = authRepository.logInUser(credentials)

        tokenManager.apply {
            setRefreshToken(tokenData.refreshToken)
            setAccessToken(tokenData.accessToken)
            setUserId(tokenData.userID)
        }
    }

}
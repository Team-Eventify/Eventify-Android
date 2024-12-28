package com.example.eventify.domain.usecases.account

import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthUserRepository
) {
    suspend operator fun invoke(credentials: UserCredentials): Result<Unit, DataError> =
        when (val result = authRepository.logInUser(credentials)){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val tokenData = result.data
                tokenManager.apply {
                    setRefreshToken(tokenData.refreshToken)
                    setAccessToken(tokenData.accessToken)
                    setUserId(tokenData.userID)
                }
                Result.Success(Unit)
            }
        }
    }


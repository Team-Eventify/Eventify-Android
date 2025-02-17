package com.example.eventify.domain.usecases.auth

import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val authRepository: AuthUserRepository
) {
    suspend operator fun invoke(credentials: UserCredentials): Result<Unit, DataError> =
        when (val result = authRepository.logInUser(credentials)){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val tokenData = result.data
                tokenProvider.apply {
                    setRefreshToken(tokenData.refreshToken)
                    setAccessToken(tokenData.accessToken)
                    setUserId(tokenData.userID)
                }
                Result.Success(Unit)
            }
        }
    }


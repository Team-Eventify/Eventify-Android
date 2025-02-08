package com.example.eventify.domain.usecases.auth

import com.example.eventify.domain.models.UserCreate
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenManager: TokenManager
) {
    suspend operator fun invoke(user: UserCreate): Result<Unit, DataError>  =
        when (val result = authRepository.registerUser(user = user)){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val tokenData = result.data
                tokenManager.apply {
                    setAccessToken(tokenData.accessToken)
                    setRefreshToken(tokenData.refreshToken)
                    setUserId(tokenData.userID)
                }
                Result.Success(Unit)
            }
        }
    }

package com.example.eventify.domain.usecases.auth

import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.OtpUserCreate
import javax.inject.Inject

class OtpRegisterUseCase @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(userData: OtpUserCreate): Result<Unit, DataError> {
        // TODO это мок
        if (userData.code != "999999") {
            return Result.Error(DataError.Network.BAD_REQUEST)
        }
        return when (val result = authRepository.otpRegisterUser(user = userData)) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val tokenData = result.data
                tokenProvider.apply {
                    setAccessToken(tokenData.accessToken)
                    setRefreshToken(tokenData.refreshToken)
                    setUserId(tokenData.userID)
                }
                Result.Success(Unit)
            }
        }
    }
}
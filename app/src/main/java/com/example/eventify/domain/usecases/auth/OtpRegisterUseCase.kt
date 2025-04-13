package com.example.eventify.domain.usecases.auth

import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.domain.models.OtpUserCreate
import javax.inject.Inject


class IncorrectOtpException : Exception("Incorrect OTP value")

fun Throwable.isIncorrectOtp() = this is IncorrectOtpException


class OtpRegisterUseCase @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(userData: OtpUserCreate) {
        // TODO это мок
        if (userData.code != "999999") {
            throw IncorrectOtpException()
        }

        authRepository.otpRegisterUser(user = userData).let { tokenData ->
            tokenProvider.apply {
                setAccessToken(tokenData.accessToken)
                setRefreshToken(tokenData.refreshToken)
                setUserId(tokenData.userID)
            }
        }
    }
}
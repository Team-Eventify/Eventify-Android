package domain.auth

import core.common.secure.tokens.TokenProvider
import data.models.OtpUserCreate
import data.repositories.auth.AuthUserRepository
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
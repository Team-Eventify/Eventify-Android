package core.domain.auth

import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val authRepository: AuthUserRepository
) {
    suspend operator fun invoke(credentials: UserCredentials): Unit {
        authRepository.logInUser(credentials).let { tokenData ->
            tokenProvider.apply {
                setRefreshToken(tokenData.refreshToken)
                setAccessToken(tokenData.accessToken)
                setUserId(tokenData.userID)
            }
        }
    }
}


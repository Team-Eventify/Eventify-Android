package domain.auth

import core.common.secure.tokens.TokenProvider
import data.models.UserCreate
import data.repositories.auth.AuthUserRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(user: UserCreate) {
        authRepository.registerUser(user = user).let { tokenData ->
            tokenProvider.apply {
                setAccessToken(tokenData.accessToken)
                setRefreshToken(tokenData.refreshToken)
                setUserId(tokenData.userID)
            }
        }
    }

}

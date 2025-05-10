package core.domain.auth

import com.example.eventify.domain.models.UserCreate
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.data.repositories.tokens.TokenProvider
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

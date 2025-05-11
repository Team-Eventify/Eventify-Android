package domain.account

import core.common.secure.tokens.TokenProvider
import data.models.User
import data.repositories.users.UsersRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): User {
        val userId = tokenProvider.getUserId() ?: throw Exception()
        return usersRepository.getUserInfo(userId = userId)
    }
}
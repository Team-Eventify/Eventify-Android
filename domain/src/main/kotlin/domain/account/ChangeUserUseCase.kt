package domain.account

import core.common.secure.tokens.TokenProvider
import data.models.User
import data.models.UserChange
import data.repositories.users.UsersRepository
import javax.inject.Inject

class ChangeUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(data: UserChange): User {
        val userId = tokenProvider.getUserId() ?: throw Exception()
        return usersRepository.changeUser(
            userId = userId,
            user = data
        )
    }
}
package domain.account

import core.common.secure.tokens.TokenProvider
import data.repositories.users.UsersRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke() {
        val userId = tokenProvider.getUserId() ?: throw NullPointerException()
        usersRepository.deleteUser(userId)

        tokenProvider.clear()
    }
}
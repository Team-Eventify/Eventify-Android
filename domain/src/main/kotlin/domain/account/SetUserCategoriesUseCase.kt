package domain.account

import core.common.secure.tokens.TokenProvider
import data.repositories.users.UsersRepository
import javax.inject.Inject

class SetUserCategoriesUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(categoryIds: List<String>): Unit {
        val userId = tokenProvider.getUserId() ?: throw Exception()
        return usersRepository.setUserCategories(userId = userId, categories = categoryIds)
    }
}
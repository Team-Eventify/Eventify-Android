package domain

import core.common.secure.tokens.TokenProvider
import data.repositories.users.UsersRepository
import data.repositories.category.CategoryRepository
import javax.inject.Inject

class GetCategoriesWithUserSelection @Inject constructor(
    private val usersRepository: UsersRepository,
    private val categoriesRepository: CategoryRepository,
    private val tokenProvider: TokenProvider
){
    suspend operator fun invoke(): List<CategorySelectItem> {
        val userId = tokenProvider.getUserId() ?: throw Exception()

        val allCategories = categoriesRepository.getCategoriesList()

        val userCategoriesId = usersRepository.getUserCategories(userId = userId)
            .map { it.id }
            .toSet()

        return allCategories.map { category ->
            CategorySelectItem(
                id = category.id,
                title = category.title,
                selected = userCategoriesId.contains(category.id),
                color = category.color.toColorOrNull()!!
            )
        }
    }
}
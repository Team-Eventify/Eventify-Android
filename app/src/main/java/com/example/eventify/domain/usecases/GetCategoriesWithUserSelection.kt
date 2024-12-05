package com.example.eventify.domain.usecases

import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.presentation.models.CategorySelectItem
import javax.inject.Inject

class GetCategoriesWithUserSelection @Inject constructor(
    private val usersRepository: UsersRepository,
    private val categoriesRepository: CategoryRepository
){
    suspend operator fun invoke(userId: String): List<CategorySelectItem>{
        val allCategories = categoriesRepository.getCategoriesList()
        val userCategoriesId = usersRepository.getUserCategories(userId = userId).map { it.id }.toSet()

        return allCategories.map { category ->
            CategorySelectItem(
                id = category.id,
                title = category.title,
                selected = userCategoriesId.contains(category.id)
            )
        }

    }
}
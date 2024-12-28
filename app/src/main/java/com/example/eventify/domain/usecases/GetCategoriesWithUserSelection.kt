package com.example.eventify.domain.usecases

import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.presentation.models.CategorySelectItem
import javax.inject.Inject

class GetCategoriesWithUserSelection @Inject constructor(
    private val usersRepository: UsersRepository,
    private val categoriesRepository: CategoryRepository,
    private val tokenManager: TokenManager
){
    suspend operator fun invoke(): Result<List<CategorySelectItem>, DataError>{
        val userId = tokenManager.getUserId() ?: throw Exception()

        val allCategories = when (val result = categoriesRepository.getCategoriesList()){
            is Result.Error -> return Result.Error(result.error)
            is Result.Success -> result.data
        }
        val userCategoriesId = when (val result = usersRepository.getUserCategories(userId = userId)){
            is Result.Error -> return Result.Error(result.error)
            is Result.Success -> result.data
        }.map { it.id }.toSet()


        return Result.Success(
            allCategories.map { category ->
                CategorySelectItem(
                    id = category.id,
                    title = category.title,
                    selected = userCategoriesId.contains(category.id)
                )
            }
        )

    }
}
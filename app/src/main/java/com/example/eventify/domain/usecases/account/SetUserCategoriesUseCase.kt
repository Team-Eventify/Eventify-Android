package com.example.eventify.domain.usecases.account

import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

class SetUserCategoriesUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(userId: String, categoryIds: List<String>){
        usersRepository.setUserCategories(userId = userId, categories = categoryIds)
    }
}
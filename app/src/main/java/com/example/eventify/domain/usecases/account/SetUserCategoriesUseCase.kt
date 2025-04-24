package com.example.eventify.domain.usecases.account

import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.users.UsersRepository
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
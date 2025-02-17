package com.example.eventify.domain.usecases.account

import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class SetUserCategoriesUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(categoryIds: List<String>): Result<Unit, DataError>{
        val userId = tokenProvider.getUserId() ?: throw Exception()
        return usersRepository.setUserCategories(userId = userId, categories = categoryIds)
    }
}
package com.example.eventify.domain.usecases.account

import com.example.eventify.domain.models.User
import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.users.UsersRepository
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
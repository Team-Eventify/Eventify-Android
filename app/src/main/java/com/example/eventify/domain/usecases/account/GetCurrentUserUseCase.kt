package com.example.eventify.domain.usecases.account

import com.example.eventify.data.exceptions.NotAuthenticated
import com.example.eventify.domain.models.User
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val tokenManager: TokenManager,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Result<User, DataError> {
        val userId = tokenManager.getUserId() ?: throw NotAuthenticated()
        return usersRepository.getUserInfo(userId = userId)
    }
}
package com.example.eventify.domain.usecases

import com.example.eventify.data.errors.NotAuthenticated
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(
    private val tokenManager: TokenManager,
    private val usersRepository: UsersRepository
) {
    suspend fun getCurrentUser(): UserInfo {
        val userId = tokenManager.getUserId() ?: throw NotAuthenticated()
        return usersRepository.getUserInfo(userId = userId)
    }
}
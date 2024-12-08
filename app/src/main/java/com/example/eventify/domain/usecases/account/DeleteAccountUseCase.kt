package com.example.eventify.domain.usecases.account

import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenManager: TokenManager
) {
    suspend operator fun invoke() {
        val userId = tokenManager.getUserId() ?: throw NullPointerException()
        usersRepository.deleteUser(userId)

        tokenManager.clear()
    }
}
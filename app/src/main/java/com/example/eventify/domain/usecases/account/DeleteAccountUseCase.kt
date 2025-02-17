package com.example.eventify.domain.usecases.account

import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke() {
        val userId = tokenProvider.getUserId() ?: throw NullPointerException()
        usersRepository.deleteUser(userId)

        tokenProvider.clear()
    }
}
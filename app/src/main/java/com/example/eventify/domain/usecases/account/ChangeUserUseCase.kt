package com.example.eventify.domain.usecases.account

import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class ChangeUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenManager: TokenManager
) {
    suspend operator fun invoke(data: UserChange): Result<UserInfo, DataError> {
        val userId = tokenManager.getUserId() ?: throw Exception()
        return usersRepository.changeUser(
            userId = userId,
            user = data
        )
    }
}
package com.example.eventify.domain.usecases.account

import com.example.eventify.data.models.UserChange
import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

class ChangeUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(userId: String, data: UserChange){
        usersRepository.changeUser(
            userId = userId,
            user = data
        )
    }
}
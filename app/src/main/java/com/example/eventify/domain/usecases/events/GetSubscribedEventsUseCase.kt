package com.example.eventify.domain.usecases.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class GetSubscribedEventsUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenManager: TokenManager
) {
    suspend operator fun invoke(): Result<List<Event>, DataError> {
        // TODO provide nullable user id result
        val userId = tokenManager.getUserId() ?: throw Exception()
        return usersRepository.getUserSubscribedEvents(userId = userId)
    }
}
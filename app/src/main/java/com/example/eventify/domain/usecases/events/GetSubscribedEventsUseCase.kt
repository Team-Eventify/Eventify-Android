package com.example.eventify.domain.usecases.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.repositories.tokens.TokenProvider
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class GetSubscribedEventsUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(): Result<List<Event>, DataError> {
        return tokenProvider.getUserId()?.let { userId ->
            usersRepository.getUserSubscribedEvents(userId = userId)
        } ?: Result.Error(DataError.Network.UNAUTHORIZED)
    }
}
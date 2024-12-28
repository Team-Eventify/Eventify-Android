package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import javax.inject.Inject

class UnsubscribeForEventUseCase @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val tokenManager: TokenManager
) {
    suspend operator fun invoke(eventId: String): Result<Unit, DataError> {
        // TODO provide detail exception
        return tokenManager.getUserId()?.let {
            eventsRepository.unsubscribeForEvent(eventId = eventId, userId = it)
        } ?: throw Exception()
    }
}
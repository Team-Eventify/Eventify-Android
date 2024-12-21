package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.tokens.TokenManager
import javax.inject.Inject

class UnsubscribeForEventUseCase @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val tokenManager: TokenManager
) {
    suspend operator fun invoke(eventId: String) {
        // TODO provide detail exception
        tokenManager.getUserId()?.apply {
            eventsRepository.unsubscribeForEvent(eventId = eventId, userId = this)
        } ?: throw Exception("UserId не найден")
    }
}
package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.events.EventsRepository
import javax.inject.Inject

class GetEventDetailUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository
) {
    suspend operator fun invoke(eventId: String) = eventsApiRepository.getEventDetail(eventId = eventId)
}
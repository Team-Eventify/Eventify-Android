package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.events.EventsRepository
import javax.inject.Inject

class SubscribeForEventUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {
    suspend operator fun invoke(eventId: String) =  eventsRepository.subscribeForEvent(eventId)
}
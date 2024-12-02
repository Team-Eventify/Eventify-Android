package com.example.eventify.domain.usecases.events

import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.data.repositories.events.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository
) {
    suspend operator fun invoke(filter: EventsFilterData? = null) = eventsApiRepository.getEventsList(filter = filter)
}
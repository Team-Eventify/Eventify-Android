package domain.events

import data.repositories.events.EventsRepository
import data.remote.models.events.EventsFilterData
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository
) {
    suspend operator fun invoke(filter: EventsFilterData? = null) = eventsApiRepository.getEventsList(filter = filter)
}
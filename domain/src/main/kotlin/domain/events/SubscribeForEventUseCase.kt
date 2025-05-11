package domain.events

import data.repositories.events.EventsRepository
import javax.inject.Inject

/**
 * Use case that provide **subscribe** for event action
 * @param eventId string UUID of event
 */
class SubscribeForEventUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {
    suspend operator fun invoke(eventId: String) =  eventsRepository.subscribeForEvent(eventId)
}
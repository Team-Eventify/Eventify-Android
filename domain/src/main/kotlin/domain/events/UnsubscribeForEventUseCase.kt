package domain.events

import core.common.secure.tokens.TokenProvider
import data.repositories.events.EventsRepository
import javax.inject.Inject

class UnsubscribeForEventUseCase @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(eventId: String) {
        // TODO provide detail exception
        return tokenProvider.getUserId()?.let {
            eventsRepository.unsubscribeForEvent(eventId = eventId, userId = it)
        } ?: throw Exception()
    }
}
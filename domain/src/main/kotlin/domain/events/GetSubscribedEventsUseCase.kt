package domain.events

import core.common.secure.tokens.TokenProvider
import data.models.Event
import data.repositories.users.UsersRepository
import javax.inject.Inject

class GetSubscribedEventsUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider
) {
    suspend operator fun invoke(): List<Event> {
        return tokenProvider.getUserId()?.let { userId ->
            usersRepository.getUserSubscribedEvents(userId = userId)
        } ?: throw Exception() // TODO написать ошибку
    }
}
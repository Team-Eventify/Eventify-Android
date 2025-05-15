package domain.events

import core.common.exceptions.UnauthorizedException
import core.common.secure.tokens.TokenProvider
import data.models.Event
import data.repositories.events.EventsRepository
import data.remote.models.events.EventsFilterData
import data.repositories.users.UsersRepository
import javax.inject.Inject



class GetEventsUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository,
    private val usersRepository: UsersRepository,
    private val tokenProvider: TokenProvider

) {
    /**
     * @param filter data to filter data. Applies if set
     * @param includeUserCategories if true collects user selected categories and use it in search.
     * If categories passed in [filter] gets intersect of both
     **/
    suspend operator fun invoke(filter: EventsFilterData? = null, includeUserCategories: Boolean = false): List<Event> {
        if (!includeUserCategories) {
            return eventsApiRepository.getEventsList(filter = filter)
        }
        val userId = tokenProvider.getUserId() ?: throw UnauthorizedException
        val userCategoriesId = usersRepository.getUserCategories(userId)
            .map { it.id }
            .toSet()

        val filterData = filter?.copy(
            categoryIds = filter.categoryIds
                ?.toSet()
                ?.intersect(userCategoriesId)
                ?.toList()
        ) ?: EventsFilterData(
            categoryIds = userCategoriesId.toList()
        )

        return eventsApiRepository.getEventsList(
            filter = filterData
        )
    }
}
package domain.events

import javax.inject.Inject
import data.repositories.events.EventsRepository
import data.repositories.category.CategoryRepository
import data.repositories.organizations.OrganizationsRepository
import domain.models.FullEventDetail

/**
 * Use case to get event with attached categories data.
 */
class GetEventDetailUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository,
    private val categoryRepository: CategoryRepository,
    private val organizationsRepository: OrganizationsRepository,
) {
    /***
     * Returns Result with EventWithCategories data
     * which contain information about event and his attached categories
     * @param eventId string UUID of event
     */
    suspend operator fun invoke(eventId: String): FullEventDetail {
        val event = eventsApiRepository.getEventDetail(eventId = eventId)

        val categories = event.categories?.map {
            categoryRepository.readCategory(it)
        } ?: emptyList()

        val organization = organizationsRepository.getOrganization(event.organizationID)

        return FullEventDetail(
            eventInfo = event,
            categories = categories,
            organization = organization,
        )
    }
}
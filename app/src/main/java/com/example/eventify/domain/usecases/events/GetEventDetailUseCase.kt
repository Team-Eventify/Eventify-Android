package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.organizations.OrganizationsRepository
import javax.inject.Inject
import com.example.eventify.domain.models.FullEventDetail

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
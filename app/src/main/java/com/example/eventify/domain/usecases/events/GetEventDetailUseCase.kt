package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.data.repositories.organizations.OrganizationsRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.models.EventWithCategories
import javax.inject.Inject
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.FullEventDetail
import timber.log.Timber

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
    suspend operator fun invoke(eventId: String): Result<FullEventDetail, DataError>{
        val event = when (val result = eventsApiRepository.getEventDetail(eventId = eventId)){
            is Result.Error -> return Result.Error(result.error)
            is Result.Success -> result.data
        }
        val categories = event.categories?.mapNotNull {
            when (val result = categoryRepository.readCategory(it)){
                is Result.Error -> {
                    Timber.w("Category with UUID($it) not found")
                    null
                }
                is Result.Success -> result.data
            }
        } ?: emptyList()
        val organization = when (val result = organizationsRepository.getOrganization(event.organizationID)) {
            is Result.Error -> return Result.Error(result.error)
            is Result.Success -> result.data
        }

        return Result.Success(FullEventDetail(
            eventInfo = event,
            categories = categories,
            organization = organization,
        ))
    }
}
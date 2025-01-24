package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.models.EventWithCategories
import javax.inject.Inject
import com.example.eventify.domain.Result

/**
 * Use case to get event with attached categories data.
 */
class GetEventDetailUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository,
    private val categoryRepository: CategoryRepository
) {
    /***
     * Returns Result with EventWithCategories data
     * which contain information about event and his attached categories
     * @param eventId string UUID of event
     */
    suspend operator fun invoke(eventId: String): Result<EventWithCategories, DataError>{
        val event = when (val result = eventsApiRepository.getEventDetail(eventId = eventId)){
            is Result.Error -> return Result.Error(result.error)
            is Result.Success -> result.data
        }
        val categories = event.categories?.map {
            when (val result = categoryRepository.readCategory(it)){
                is Result.Error -> return Result.Error(result.error)
                is Result.Success -> result.data
            }
        } ?: emptyList()

        return  Result.Success(
            event.let {
                EventWithCategories(
                    title = it.title,
                    id = it.id,
                    capacity = it.capacity,
                    cover = it.cover,
                    description = it.description,
                    end = it.end,
                    organizationID = it.organizationID,
                    start = it.start,
                    state = it.state,
                    location = it.location,
                    subscribed = it.subscribed,
                    categories = categories,
                )
            }
        )
    }
}
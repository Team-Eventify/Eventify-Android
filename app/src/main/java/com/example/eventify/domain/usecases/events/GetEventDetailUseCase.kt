package com.example.eventify.domain.usecases.events

import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.domain.models.EventWithCategories
import javax.inject.Inject

class GetEventDetailUseCase @Inject constructor(
    private val eventsApiRepository: EventsRepository,
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(eventId: String): EventWithCategories {
        val event =  eventsApiRepository.getEventDetail(eventId = eventId)
        val categories = event.categories?.map {
            categoryRepository.readCategory(it)
        } ?: emptyList()
        return  event.let {
            EventWithCategories(
                title = it.title,
                id = it.id,
                createdAt = it.createdAt,
                modifiedAt = it.modifiedAt,
                capacity = it.capacity,
                cover = it.cover,
                description = it.description,
                end = it.end,
                moderated = it.moderated,
                ownerID = it.ownerID,
                start = it.start,
                state = it.state,
                location = it.location,
                subscribed = it.subscribed,
                categories = categories,
            )
        }
    }
}
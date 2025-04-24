package com.example.eventify.data.repositories.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.data.remote.models.events.toEventDetail
import com.example.eventify.data.remote.models.events.toEventInfo
import com.example.eventify.data.remote.utils.handle
import javax.inject.Inject
import com.example.eventify.domain.models.EventDetail

class EventRepositoryImpl @Inject constructor(
    private val dataSource: EventsAPI
) : EventsRepository {
    override suspend fun getEventsList(filter: EventsFilterData?): List<Event> {
        return dataSource.getEventsList(
            limit = filter?.limit,
            offset = filter?.offset,
            ownerID = filter?.ownerId,
            start = filter?.start,
            end = filter?.end,
            categoryIds = filter?.validCategoryIds,
            title = filter?.title,
        ).handle {
            transformSuccess { body ->
                body.map { it.toEventInfo() }
            }
            process(404){
                emptyList()
            }
        }

    }

    override suspend fun getEventDetail(eventId: String): EventDetail  {
        return dataSource.getEvent(eventId = eventId).handle {
            transformSuccess { it.toEventDetail() }
        }
    }

    override suspend fun subscribeForEvent(eventId: String): Unit {
        return dataSource.subscribeForEvent(eventId = eventId).handle()
    }

    override suspend fun unsubscribeForEvent(eventId: String, userId: String): Unit {
        return dataSource.unsubscribeForEvent(eventId = eventId, userId = userId).handle()
    }
}


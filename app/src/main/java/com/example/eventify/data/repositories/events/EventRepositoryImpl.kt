package com.example.eventify.data.repositories.events

import com.example.eventify.data.exceptions.NullableResponseException
import com.example.eventify.data.exceptions.EventNotFoundException
import com.example.eventify.data.exceptions.UnprocessedServerResponseException
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.data.remote.models.events.toEventInfo
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val dataSource: EventsAPI
) : EventsRepository {
    override suspend fun getEventsList(filter: EventsFilterData?): List<EventInfo> {
        val response = dataSource.getEventsList(
            limit = filter?.limit,
            offset = filter?.offset,
            ownerID = filter?.ownerId,
            start = filter?.start,
            end = filter?.start
        )

        val events = when (response.code()) {
            200 -> response.body()?.let { eventsList ->
                eventsList.map { event -> event.toEventInfo() }
            }
            404 -> emptyList()
            else -> throw UnprocessedServerResponseException()
        }
        return events ?: throw NullableResponseException()
    }

    override suspend fun getEventDetail(eventId: String): EventInfo {
        val response = dataSource.getEvent(eventId = eventId)

        val event = when (response.code()) {
            200 -> response.body()?.toEventInfo()
            404 -> throw EventNotFoundException()
            else -> throw UnprocessedServerResponseException()
        }
        return event ?: throw NullableResponseException("Ошибка сервера.")
    }
}
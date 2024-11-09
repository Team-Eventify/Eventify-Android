package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.events.toEventInfo
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val dataSource: EventsAPI
) : EventsRepository {
    override suspend fun getEventsList(): List<EventInfo> {
        val response = dataSource.getEventsList()

        val events = when (response.code()) {
            200 -> response.body()?.let { eventsList ->
                eventsList.map { event -> event.toEventInfo() }
            }
            else -> null
        }
        return events ?: throw Exception("Ошибка сервера.")
    }

    override suspend fun getEventDetail(eventId: Int): EventInfo {
        val response = dataSource.getEvent(eventId = eventId)

        val event = when (response.code()) {
            200 -> response.body()?.toEventInfo()
            else -> null
        }
        return event ?: throw Exception("Ошибка сервера.")
    }
}
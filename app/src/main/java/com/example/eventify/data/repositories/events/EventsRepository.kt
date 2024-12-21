package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.models.events.EventsFilterData

interface EventsRepository {
    suspend fun getEventsList(filter: EventsFilterData? = null): List<EventInfo>
    suspend fun getEventDetail(eventId: String): EventInfo

    suspend fun subscribeForEvent(eventId: String): Unit
    suspend fun unsubscribeForEvent(eventId: String, userId: String): Unit
}
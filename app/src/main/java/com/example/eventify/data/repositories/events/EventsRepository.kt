package com.example.eventify.data.repositories.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.models.EventDetail

interface EventsRepository {
    suspend fun getEventsList(filter: EventsFilterData? = null): List<Event>
    suspend fun getEventDetail(eventId: String): EventDetail
    suspend fun subscribeForEvent(eventId: String): Unit
    suspend fun unsubscribeForEvent(eventId: String, userId: String): Unit
}
package com.example.eventify.data.repositories.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface EventsRepository {
    suspend fun getEventsList(filter: EventsFilterData? = null): Result<List<Event>, DataError>
    suspend fun getEventDetail(eventId: String): Result<Event, DataError>

    suspend fun subscribeForEvent(eventId: String): Result<Unit, DataError>
    suspend fun unsubscribeForEvent(eventId: String, userId: String): Result<Unit, DataError>
}
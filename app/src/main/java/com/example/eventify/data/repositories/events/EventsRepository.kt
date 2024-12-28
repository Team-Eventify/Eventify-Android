package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface EventsRepository {
    suspend fun getEventsList(filter: EventsFilterData? = null): Result<List<EventInfo>, DataError>
    suspend fun getEventDetail(eventId: String): Result<EventInfo, DataError>

    suspend fun subscribeForEvent(eventId: String): Result<Unit, DataError>
    suspend fun unsubscribeForEvent(eventId: String, userId: String): Result<Unit, DataError>
}
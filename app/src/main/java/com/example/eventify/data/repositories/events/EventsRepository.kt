package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.models.events.CreateEventRequest
import com.example.eventify.data.remote.models.events.EventInfoResponse
import retrofit2.Response

interface EventsRepository {
    suspend fun getEventsList(): List<EventInfo>
    suspend fun getEventDetail(eventId: Int): EventInfo
}
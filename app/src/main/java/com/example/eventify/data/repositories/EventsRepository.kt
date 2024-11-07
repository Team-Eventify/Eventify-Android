package com.example.eventify.data.repositories

import com.example.eventify.data.remote.models.events.CreateEventRequest
import com.example.eventify.data.remote.models.events.EventInfoResponse
import retrofit2.Response

interface EventsRepository {
    suspend fun getEventsList(): Response<List<EventInfoResponse>>
    suspend fun getEvent(eventId: Int): Response<EventInfoResponse>
}
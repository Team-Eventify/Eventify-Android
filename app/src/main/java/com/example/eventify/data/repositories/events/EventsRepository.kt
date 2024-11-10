package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo

interface EventsRepository {
    suspend fun getEventsList(): List<EventInfo>
    suspend fun getEventDetail(eventId: String): EventInfo
}
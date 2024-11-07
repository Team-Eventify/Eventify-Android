package com.example.eventify.data.repositories

import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.events.EventInfoResponse
import retrofit2.Response
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val dataSource: EventsAPI
) : EventsRepository {
    override suspend fun getEventsList(): Response<List<EventInfoResponse>> = dataSource.getEventsList()

    override suspend fun getEvent(eventId: Int): Response<EventInfoResponse> = dataSource.getEvent(eventId = eventId)
}
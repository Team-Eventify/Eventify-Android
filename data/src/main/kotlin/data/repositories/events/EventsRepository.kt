package data.repositories.events

import data.models.Event
import data.models.EventDetail
import data.remote.models.events.EventsFilterData

interface EventsRepository {
    suspend fun getEventsList(filter: EventsFilterData? = null): List<Event>
    suspend fun getEventDetail(eventId: String): EventDetail
    suspend fun subscribeForEvent(eventId: String): Unit
    suspend fun unsubscribeForEvent(eventId: String, userId: String): Unit
}
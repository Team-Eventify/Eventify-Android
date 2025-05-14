package data.repositories.events

import javax.inject.Inject
import data.models.Event
import data.models.EventDetail
import data.models.toBusiness
import data.remote.models.events.EventsFilterData
import data.remote.api.EventsAPI
import data.remote.utils.handle

class EventRepositoryImpl @Inject constructor(
    private val dataSource: EventsAPI
) : EventsRepository {
    override suspend fun getEventsList(filter: EventsFilterData?): List<Event> {
        return dataSource.getEventsList(
            limit = filter?.limit,
            offset = filter?.offset,
            ownerID = filter?.ownerId,
            start = filter?.start,
            end = filter?.end,
            categoryIds = filter?.validCategoryIds,
            title = filter?.title,
        ).handle {
            transformSuccess { body ->
                body.map { it.toBusiness() }
            }
            process(404){
                emptyList()
            }
        }

    }

    override suspend fun getEventDetail(eventId: String): EventDetail {
        return dataSource.getEvent(eventId = eventId).handle {
            transformSuccess { it.toBusiness() }
        }
    }

    override suspend fun subscribeForEvent(eventId: String): Unit {
        return dataSource.subscribeForEvent(eventId = eventId).handle()
    }

    override suspend fun unsubscribeForEvent(eventId: String, userId: String): Unit {
        return dataSource.unsubscribeForEvent(eventId = eventId, userId = userId).handle()
    }
}


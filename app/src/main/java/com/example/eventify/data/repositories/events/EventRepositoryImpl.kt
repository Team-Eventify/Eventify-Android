package com.example.eventify.data.repositories.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.data.remote.models.events.toEventDetail
import com.example.eventify.data.remote.models.events.toEventInfo
import com.example.eventify.data.remote.utils.handle
import com.example.eventify.domain.DataError
import javax.inject.Inject
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.EventDetail
import timber.log.Timber

class EventRepositoryImpl @Inject constructor(
    private val dataSource: EventsAPI
) : EventsRepository {
    override suspend fun getEventsList(filter: EventsFilterData?): Result<List<Event>, DataError> = try {
        dataSource.getEventsList(
            limit = filter?.limit,
            offset = filter?.offset,
            ownerID = filter?.ownerId,
            start = filter?.start,
            end = filter?.end,
            categoryIds = filter?.validCategoryIds,
            title = filter?.title,
        ).handle {
            transformSuccess { body ->
                body.map { it.toEventInfo() }
            }
            process(404){
                Result.Success(emptyList())
            }
        }

    } catch (e:Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun getEventDetail(eventId: String): Result<EventDetail, DataError> = try {
        dataSource.getEvent(eventId = eventId).handle {
            transformSuccess { it.toEventDetail() }
        }
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun subscribeForEvent(eventId: String): Result<Unit, DataError> = try {
        dataSource.subscribeForEvent(eventId = eventId).handle()
    } catch (e:Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun unsubscribeForEvent(eventId: String, userId: String): Result<Unit, DataError> = try {
        dataSource.unsubscribeForEvent(eventId = eventId, userId = userId).handle()
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }
}


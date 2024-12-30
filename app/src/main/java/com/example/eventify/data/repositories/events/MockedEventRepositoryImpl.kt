package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.github.javafaker.Faker
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MockedEventRepositoryImpl : EventsRepository {
    private val faker = Faker()
    private var events = List(10) { getRandomEventInfo(faker) }

    override suspend fun getEventsList(filter: EventsFilterData?): Result<List<EventInfo>, DataError> = Result.Success(events)
    override suspend fun getEventDetail(eventId: String): Result<EventInfo, DataError> = events.find { it.id == eventId }?.let {
        Result.Success(it)
    } ?: Result.Error(DataError.Network.NOT_FOUND)
    override suspend fun subscribeForEvent(eventId: String): Result<Unit, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun unsubscribeForEvent(eventId: String, userId: String): Result<Unit, DataError> {
        TODO("Not yet implemented")
    }

    private fun getRandomEventInfo(faker: Faker): EventInfo = EventInfo(
        id = UUID.randomUUID().toString(),
        title = faker.lorem().sentence(),
        description = faker.lorem().paragraph(),
        createdAt = faker.date().past(30, TimeUnit.DAYS).time / 1000,
        modifiedAt = faker.date().past(30, TimeUnit.DAYS).time / 1000,
        moderated = false,
        state = "",
        ownerID = UUID.randomUUID().toString(),
        capacity = Random.nextInt(10, 200),
        start = (faker.date().past(30, TimeUnit.DAYS).time / 1000).toInt(),
        end = (faker.date().past(60, TimeUnit.DAYS).time / 1000).toInt(),
        location = "",
        cover = "",
        subscribed = false
    )

}
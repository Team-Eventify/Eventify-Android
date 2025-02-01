package com.example.eventify.data.repositories.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.EventDetail
import com.github.javafaker.Faker
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MockedEventRepositoryImpl : EventsRepository {
    private val faker = Faker()
    private var events = List(10) { getRandomEventInfo(faker) }

    override suspend fun getEventsList(filter: EventsFilterData?): Result<List<Event>, DataError> = Result.Success(events)
    override suspend fun getEventDetail(eventId: String): Result<EventDetail, DataError> {
        TODO("Not yet implemented")
    }
    override suspend fun subscribeForEvent(eventId: String): Result<Unit, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun unsubscribeForEvent(eventId: String, userId: String): Result<Unit, DataError> {
        TODO("Not yet implemented")
    }

    private fun getRandomEventInfo(faker: Faker): Event = Event(
        id = UUID.randomUUID().toString(),
        title = faker.lorem().sentence(),
        description = faker.lorem().paragraph(),
        state = "",
        capacity = Random.nextInt(10, 200),
        start = faker.date().past(30, TimeUnit.DAYS).time / 1000,
        end = faker.date().past(60, TimeUnit.DAYS).time / 1000,
        location = "",
        cover = "",
        subscribed = false,
        organizationID = UUID.randomUUID().toString(),
    )

}
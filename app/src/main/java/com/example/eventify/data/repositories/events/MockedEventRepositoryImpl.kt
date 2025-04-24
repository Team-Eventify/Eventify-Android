package com.example.eventify.data.repositories.events

import com.example.eventify.domain.models.Event
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.models.EventDetail
import com.example.eventify.domain.models.EventState
import com.github.javafaker.Faker
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MockedEventRepositoryImpl : EventsRepository {
    private val faker = Faker()
    private var events = List(10) { getRandomEventInfo(faker) }

    override suspend fun getEventsList(filter: EventsFilterData?): List<Event> = events
    override suspend fun getEventDetail(eventId: String): EventDetail {
        TODO("Not yet implemented")
    }
    override suspend fun subscribeForEvent(eventId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun unsubscribeForEvent(eventId: String, userId: String) {
        TODO("Not yet implemented")
    }

    private fun getRandomEventInfo(faker: Faker): Event = Event(
        id = UUID.randomUUID().toString(),
        title = faker.lorem().sentence(),
        description = faker.lorem().paragraph(),
        state = EventState.PUBLISHED,
        capacity = Random.nextInt(10, 200),
        start = faker.date().past(30, TimeUnit.DAYS).time / 1000,
        end = faker.date().past(60, TimeUnit.DAYS).time / 1000,
        location = "",
        cover = "",
        subscribed = false,
        organizationID = UUID.randomUUID().toString(),
    )

}
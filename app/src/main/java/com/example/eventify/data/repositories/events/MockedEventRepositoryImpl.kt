package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.github.javafaker.Faker
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MockedEventRepositoryImpl : EventsRepository {
    private val faker = Faker()
    private var events = List(10) { getRandomEventInfo(faker) }

    override suspend fun getEventsList(filter: EventsFilterData?): List<EventInfo> = events
    override suspend fun getEventDetail(eventId: String): EventInfo = events.find { it.id == eventId } ?: throw Exception("Ивент с id=$eventId не найден.")

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
        cover = ""
    )

}
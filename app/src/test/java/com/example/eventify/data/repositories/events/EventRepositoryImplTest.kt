package com.example.eventify.data.repositories.events

import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.events.EventInfoResponse
import com.example.eventify.data.remote.models.events.toEventInfo
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.emptyResponseError
import com.example.eventify.responseSuccess
import com.github.javafaker.Faker
import io.mockk.impl.annotations.MockK
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.random.Random


@ExtendWith(MockKExtension::class)
class EventRepositoryImplTest {

    @MockK
    private lateinit var api: EventsAPI

    private lateinit var repository: EventRepositoryImpl
    private val faker = Faker()
    private val lorem = faker.lorem()

    private fun getFakeEventInfoResponse() = EventInfoResponse(
        id = UUID.randomUUID().toString(),
        title = lorem.words(3).joinToString(" "),
        description = lorem.paragraph(),
        start = Random.nextInt(),
        end = Random.nextInt(),
        cover = "",
        state = lorem.word(),
        CreatedAt = Random.nextLong(),
        ModifiedAt = Random.nextLong(),
        capacity = Random.nextInt(0, 100),
        moderated = Random.nextBoolean(),
        ownerID = UUID.randomUUID().toString(),
        location = lorem.words(4).joinToString(" "),
        subscribed = Random.nextBoolean(),
        categories = List(Random.nextInt(1, 4)){
                UUID.randomUUID().toString()
            },
        )

    @BeforeEach
    fun setUp() {
        repository = EventRepositoryImpl(api)
    }

    @Test
    fun `getEventsList should return not found result error if API return 404 response code`() = runTest {
        coEvery { api.getEventsList() } returns emptyResponseError(404)

        val result = repository.getEventsList()

        assertThat(result).apply {
            isInstanceOf(Result.Error::class.java)
        }

        val errorResult = result as Result.Error

        assertThat(errorResult.error).apply {
            isEqualTo(DataError.Network.NOT_FOUND)
        }
    }

    @Test
    fun `getEventDetail should return result success data when found Event and transform it to EventInfo`() = runTest {
        val eventResponse = getFakeEventInfoResponse()

        coEvery { api.getEvent(eventId = eventResponse.id) } returns responseSuccess(eventResponse)

        val result = repository.getEventDetail(eventId = eventResponse.id)

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }

        val successResult = result as Result.Success

        assertThat(successResult.data).apply {
            isEqualTo(eventResponse.toEventInfo())
        }
    }

    @Test
    fun `getEventDetail should return result error when not found`() = runTest {
        coEvery { api.getEvent(any()) } returns emptyResponseError(404)

        val result = repository.getEventDetail(UUID.randomUUID().toString())

        assertThat(result).apply {
            isInstanceOf(Result.Error::class.java)
        }

        val errorResult = result as Result.Error

        assertThat(errorResult.error).apply {
            isEqualTo(DataError.Network.NOT_FOUND)
        }
    }

    @Test
    fun `subscribeForEvent always returns success result`() = runTest {
        coEvery { api.subscribeForEvent(any()) } returns responseSuccess(Unit)

        val result = repository.subscribeForEvent(UUID.randomUUID().toString())

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }

        val successResult = result as Result.Success

        assertThat(successResult.data).apply {
            isEqualTo(Unit)
        }
    }

    // TODO написать тест для отписки, уточнить поведение ручки

//    @Test
//    fun subscribeForEvent() {
//    }
//
//    @Test
//    fun unsubscribeForEvent() {
//    }
}
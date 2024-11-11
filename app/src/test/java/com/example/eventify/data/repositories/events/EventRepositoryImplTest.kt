package com.example.eventify.data.repositories.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.repositories.auth.AuthUserRepositoryImpl
import com.example.eventify.data.repositories.tokens.MockedTokenManagerImpl
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventRepositoryImplTest {
    private val mockedTokenManager = MockedTokenManagerImpl()
    private val accessTokenInterceptor = AccessTokenInterceptor(tokenManager = mockedTokenManager)
    private val authDataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)
    private val authRepository = AuthUserRepositoryImpl(authDataSource)
    private val tokenAuthenticator = TokenAuthenticator(tokenManager = mockedTokenManager, authRepository = authRepository)
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(accessTokenInterceptor)
        .authenticator(tokenAuthenticator)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    private val eventsApi = Retrofit.Builder()
        .baseUrl("http://188.225.82.113:8090/api/v1/events/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(EventsAPI::class.java)
    private val eventsRepository = EventRepositoryImpl(dataSource = eventsApi)

    @BeforeEach
    fun setUp(): Unit = runBlocking{
        val tokenData = authRepository.logInUser(UserCredentials("r", "r"))
        mockedTokenManager.apply {
            setAccessToken(tokenData.accessToken)
            setRefreshToken(tokenData.refreshToken)
            setUserId(tokenData.userID)
        }
    }

    @Test
    fun getEventsList(): Unit = runBlocking {
        val events = eventsRepository.getEventsList()
        assertNotEquals(events.size, 0)
    }

    @ParameterizedTest(name = "{index} => eventId={0}, expectedEvent={1} expectedException={2}")
    @MethodSource("eventDetailUseCase")
    fun getEventDetail(eventId: String, expectedEvent: EventInfo?, expectedException: Class<out Exception>?) {
        expectedException?.let {
            runBlocking { eventsRepository.getEventDetail(eventId = eventId) }
        } ?: {
            val event = runBlocking { eventsRepository.getEventDetail(eventId = eventId) }
            assertEquals(expectedEvent, event)
        }
    }

    companion object {
        @JvmStatic
        fun eventDetailUseCase() = listOf(
            Arguments.of("963753c3-e8a7-4e74-8aa7-c39d738c9684", EventInfo(
                state = "CREATED",
                title = "Bug fixed",
                description = "Good job!",
                start = 1731346290,
                end = 1731357060,
                capacity = 0,
                moderated = false,
                createdAt = 1731346320405,
                modifiedAt = 1731346320405,
                id = "963753c3-e8a7-4e74-8aa7-c39d738c9684",
                ownerID = "1e2e88d0-6618-478b-8f2f-ccc123aa261f"

            ), null),
        )
    }

}
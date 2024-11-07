package com.example.eventify

import com.example.eventify.data.remote.NetworkServiceFactory
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import com.example.eventify.data.repositories.EventRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

//class EventsRepositoryUnitTest {
//    private val authDataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)
//    private val authRepository = AuthUserRepositoryImpl(authDataSource)
//
//    private val eventsDataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/events/", EventsAPI::class.java)
//    private val eventsRepository = EventRepositoryImpl(eventsDataSource)
//
//    @Test
//    fun getAllEventsTest() = runBlocking{
//        val authResponse = authRepository.logInUser(
//            LogInRequestData(
//                email = "test@gmail.com",
//                password = "password"
//            )
//        )
//
//        assertEquals(200, authResponse.code())
//        assertNotNull(authResponse.body()?.accessToken)
//
//        val accessToken = "Bearer ${authResponse.body()!!.accessToken}"
//
//
//        val eventsResponse = eventsRepository.getEventsList(accessToken)
//
//        assertEquals(200, eventsResponse.code())
//    }
//}
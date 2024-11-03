package com.example.eventify

import com.example.eventify.data.remote.NetworkServiceFactory
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AuthRepositoryUnitTest {
    private val dataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)
    private val repository = AuthUserRepositoryImpl(dataSource)

    @Test
    fun registerNewUserTest() = runBlocking{
        val newUserPayload = RegisterUserRequestData(
            email = "test@gmail.com",
            password = "password"
        )
        val response = repository.registerUser(newUserPayload)

        assertNotNull(response)
    }



    @Test
    fun logInUserTest() = runBlocking {
        val authData = LogInRequestData(
            email = "test@gmail.com",
            password = "password"
        )

        val response = repository.logInUser(authData)

        assertEquals(response.code(), 200)

        val responseBody = response.body()
        assertNotNull(responseBody)
    }

}
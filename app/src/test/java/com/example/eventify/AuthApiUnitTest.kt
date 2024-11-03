package com.example.eventify

import com.example.eventify.data.remote.NetworkServiceFactory
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.remote.models.auth.LogInResponse
import com.example.eventify.data.remote.models.auth.RefreshTokenRequestData
import com.example.eventify.data.remote.models.auth.RegisterUserRequestData
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

class AuthRepositoryUnitTest {
    private val dataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)
    private val repository = AuthUserRepositoryImpl(dataSource)

    @Test
    fun getPublicKeyTest() = runBlocking {
        val response = repository.getPublicKey()
        assertEquals(response.code(), 200)
    }

    @Ignore("Already created")
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

        val authResponse = repository.logInUser(authData)

        assertEquals(authResponse.code(), 200)

        val responseBody = authResponse.body()
        assertNotNull(responseBody)

        assertNotNull(responseBody?.accessToken)
        assertNotNull(responseBody?.refreshToken)
        assertNotNull(responseBody?.userID)
    }

    @Test
    fun refreshAccessTokenTest() = runBlocking {
        val authData = LogInRequestData(
            email = "test@gmail.com",
            password = "password"
        )

        val authResponse = repository.logInUser(authData)
        assertEquals(authResponse.code(), 200)

        val responseBody = authResponse.body()
        assertNotNull(responseBody)

        assertNotNull(responseBody?.accessToken)
        assertNotNull(responseBody?.refreshToken)
        assertNotNull(responseBody?.userID)

        val refreshTokenData = RefreshTokenRequestData(
            refresh = responseBody!!.refreshToken
        )
        val refreshTokenResponse = repository.refreshAccessToken(refreshTokenData)
        assertEquals(refreshTokenResponse.code(), 200)

        val refreshTokenResponseBody = refreshTokenResponse.body()
        assertNotNull(refreshTokenResponseBody?.accessToken)
        assertNotNull(refreshTokenResponseBody?.refreshToken)
        assertNotNull(refreshTokenResponseBody?.userID)

        // Compare userID from auth token data and refreshing
        assertEquals(responseBody.userID, refreshTokenResponseBody!!.userID)
    }

}
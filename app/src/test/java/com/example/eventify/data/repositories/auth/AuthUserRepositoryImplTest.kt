package com.example.eventify.data.repositories.auth

import com.example.eventify.data.errors.UserNotFoundException
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class AuthUserRepositoryImplTest {
    private val dataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java)
    private val repository = AuthUserRepositoryImpl(dataSource)

    @Disabled("Not implemented")
    @Test
    fun refreshAccessToken() {
    }

    @Disabled("Not implemented")
    @Test
    fun registerUser() {
    }

    @ParameterizedTest(name = "{index} => credentials={0}, expectedException={1}")
    @MethodSource("loginTestCases")
    fun logInUser(credentials: UserCredentials, expectedException: Class<out Exception>?) {
        expectedException?.let {
            assertThrows(expectedException){
                runBlocking { repository.logInUser(credentials) }
            }
        } ?: {
            val token = runBlocking { repository.logInUser(credentials) }
        }
    }

    companion object {
        @JvmStatic
        private fun loginTestCases() = listOf(
            Arguments.of(UserCredentials(login = "r", password = "r"), null),
            Arguments.of(UserCredentials(login = "svsdvsdv", password = "sdvsdv"), UserNotFoundException::class.java),
            Arguments.of(UserCredentials(login = "", password = ""), UserNotFoundException::class.java),
        )
    }
}
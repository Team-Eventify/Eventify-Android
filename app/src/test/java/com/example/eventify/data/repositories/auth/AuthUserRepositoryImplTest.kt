package com.example.eventify.data.repositories.auth

import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.domain.Result
import com.example.eventify.responseSuccess
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthUserRepositoryImplTest {

    @MockK
    private lateinit var api: AuthAPI
    private lateinit var repository: AuthUserRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository = AuthUserRepositoryImpl(api)
    }

    // TODO implement tests

//    @Test
//    fun `logInUser with valid payload`() = runTest {
//        val payload = LogInRequestData(
//            email = "",
//            password = ""
//        )
//        coEvery { api.logInUser(payload = payload) } returns responseSuccess()
//
//        val result = repository.logInUser(credentials = payload)
//
//        assertThat(result).apply{
//            isInstanceOf(Result.Success::class.java)
//        }
//
//        val successResult = result as Result.Success
//
//        assertThat(successResult.data).apply {
//
//        }
//    }

//    @Test
//    fun refreshAccessToken() {
//    }
//
//    @Test
//    fun registerUser() {
//    }
//
//    @Test
//    fun logInUser() {
//    }
}
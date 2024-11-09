package com.example.eventify

import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.repositories.auth.AuthUserRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

class AuthRepositoryUnitTest {
    private val dataSource = NetworkServiceFactory.getApi("http://188.225.82.113:8090/api/v1/auth/", AuthAPI::class.java, authenticator = null)
    private val repository = AuthUserRepositoryImpl(dataSource)

    @Test
    fun getPublicKeyTest() = runBlocking {

    }

    @Ignore("Already created")
    @Test
    fun registerNewUserTest() = runBlocking{

    }



    @Test
    fun logInUserTest() = runBlocking {

    }

    @Test
    fun refreshAccessTokenTest() = runBlocking {

    }

}
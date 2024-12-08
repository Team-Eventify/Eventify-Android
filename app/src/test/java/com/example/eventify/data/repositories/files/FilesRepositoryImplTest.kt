package com.example.eventify.data.repositories.files

import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.api.FilesAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.repositories.auth.AuthUserRepositoryImpl
import com.example.eventify.data.repositories.tokens.MockedTokenManagerImpl
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilesRepositoryImplTest {
    private val mockedTokenManager = MockedTokenManagerImpl()
    private val accessTokenInterceptor = AccessTokenInterceptor(tokenManager = mockedTokenManager)
    private val authDataSource = NetworkServiceFactory.getApi("https://eventify.website/api/v1/auth/", AuthAPI::class.java)
    private val authRepository = AuthUserRepositoryImpl(authDataSource)
    private val tokenAuthenticator = TokenAuthenticator(tokenManager = mockedTokenManager, authRepository = authRepository)
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(accessTokenInterceptor)
        .authenticator(tokenAuthenticator)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    private val filesApi = Retrofit.Builder()
        .baseUrl("https://eventify.website/api/v1/files/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(FilesAPI::class.java)
    private val filesRepository = FilesRepositoryImpl(dataSource = filesApi)
    
    @BeforeEach
    fun setUp(): Unit = runBlocking {
        val tokenData = authRepository.logInUser(UserCredentials("admin@mail.ru", "Qwerty128we"))
        mockedTokenManager.apply {
            setAccessToken(tokenData.accessToken)
            setRefreshToken(tokenData.refreshToken)
            setUserId(tokenData.userID)
        }
    }

    @Test
    fun getFileById() = runBlocking{
        val response = filesRepository.getFileById(fileId = "a550646b-bb5d-488f-846f-7f74427ef9b6")
        assertNotNull(response)
    }
}
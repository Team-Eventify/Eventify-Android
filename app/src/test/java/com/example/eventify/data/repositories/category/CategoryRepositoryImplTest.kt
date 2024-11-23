package com.example.eventify.data.repositories.category

import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.api.CategoryAPI
import com.example.eventify.data.remote.api.EventsAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.repositories.auth.AuthUserRepositoryImpl
import com.example.eventify.data.repositories.events.EventRepositoryImpl
import com.example.eventify.data.repositories.tokens.MockedTokenManagerImpl
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Ignore
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRepositoryImplTest {
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
    private val categoryApi = Retrofit.Builder()
        .baseUrl("https://eventify.website/api/v1/category/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(CategoryAPI::class.java)
    private val categoryRepository = CategoryRepositoryImpl(dataSource = categoryApi)


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
    fun getCategoriesList() = runBlocking{
        val categories = categoryRepository.getCategoriesList()
        assertNotNull(categories)
        assertNotEquals(categories, 0)
    }

    @Test
    fun readCategory() = runBlocking {
        val categories = categoryRepository.getCategoriesList()
        assertNotNull(categories)
        assertNotEquals(categories, 0)
        val randomCategory =  categories.shuffled().random()

        val targetCategory = categoryRepository.readCategory(randomCategory.id)

        assertEquals(randomCategory, targetCategory)
    }

    @Ignore("Think about creation test cases")
    @Test
    fun createCategory() {
    }
}
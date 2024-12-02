package com.example.eventify.data.repositories.users

import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.api.UsersAPI
import com.example.eventify.data.remote.utils.AccessTokenInterceptor
import com.example.eventify.data.remote.utils.NetworkServiceFactory
import com.example.eventify.data.remote.utils.TokenAuthenticator
import com.example.eventify.data.repositories.auth.AuthUserRepositoryImpl
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

class UsersRepositoryImplTest {
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
    private val usersApi = Retrofit.Builder()
        .baseUrl("https://eventify.website/api/v1/users/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(UsersAPI::class.java)
    private val usersRepository = UsersRepositoryImpl(dataSource = usersApi)

    @BeforeEach
    fun setUp(): Unit = runBlocking {
        val tokenData = authRepository.logInUser(UserCredentials("r", "r"))
        mockedTokenManager.apply {
            setAccessToken(tokenData.accessToken)
            setRefreshToken(tokenData.refreshToken)
            setUserId(tokenData.userID)
        }
    }

    @Ignore("Will be later")
    @Test
    fun changeUser() {
        // TODO write changeUser test
    }

    @Ignore("Will be later")
    @Test
    fun getUserInfo() = runBlocking {
        val userId = mockedTokenManager.getUserId() ?: throw NullPointerException()
        val user = usersRepository.getUserInfo(userId = userId)
        assertNotNull(user)

    }

    @Ignore("Will be later")
    @Test
    fun getUserCategories() = runBlocking {
        val userId = mockedTokenManager.getUserId() ?: throw NullPointerException()
        val user = usersRepository.getUserInfo(userId = userId)
        assertNotNull(user)

        val categories = usersRepository.getUserCategories(userId = user.id)
        assertNotNull(categories)
    }

    @Ignore("Will be later")
    @Test
    fun setUserCategories() = runBlocking{
        val userId = mockedTokenManager.getUserId() ?: throw NullPointerException()
        val user = usersRepository.getUserInfo(userId = userId)
        assertNotNull(user)

        val categoryIds = listOf("b22e7015-75db-447f-9707-b17ea130ee9d")
        usersRepository.setUserCategories(userId = user.id, categories = categoryIds)

        val categories = usersRepository.getUserCategories(user.id)
        assertEquals(categoryIds.size, categories.size)

        categoryIds.zip(categories).forEach{ (id, category) ->
            assertEquals(id, category.id)
        }
        // TODO check if category id list is empty
    }

}
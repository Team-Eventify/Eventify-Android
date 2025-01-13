package com.example.eventify.data.repositories.users

import com.example.eventify.data.remote.api.UsersAPI
import com.example.eventify.data.remote.models.category.CategoryInfoResponse
import com.example.eventify.data.remote.models.users.UserInfoResponse
import com.example.eventify.data.remote.models.users.toUserInfo
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.User
import com.example.eventify.emptyResponseError
import com.example.eventify.nextHex
import com.example.eventify.responseSuccess
import com.github.javafaker.Faker
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.random.Random


@ExtendWith(MockKExtension::class)
class UsersRepositoryImplTest {
    @MockK
    private lateinit var api: UsersAPI
    private lateinit var sut: UsersRepositoryImpl

    private val faker = Faker()
    private val lorem = faker.lorem()

    @BeforeEach
    fun setUp() {
        sut = UsersRepositoryImpl(api)
    }

    @Test
    fun `Success getting user info`() = runTest {
        val userResponse = getFakeUserInfoResponse()
        coEvery { api.getUserInfo(userId = userResponse.id) } returns responseSuccess(userResponse)

        val result = sut.getUserInfo(userId = userResponse.id)

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }
        val successResult = result as Result.Success
        assertThat(successResult.data).apply {
            isEqualTo(userResponse.toUserInfo())
        }
    }

    @Test
    fun `Error result when getting user not found`() = runTest {
        coEvery { api.getUserInfo(any()) } returns emptyResponseError(404)

        val result = sut.getUserInfo(userId = UUID.randomUUID().toString())

        assertThat(result).apply {
            isInstanceOf(Result.Error::class.java)
        }
        val errorResult = result as Result.Error
        assertThat(errorResult.error).apply {
            isEqualTo(DataError.Network.NOT_FOUND)
        }
    }

    @Test
    fun `Success getting user categories`() = runTest {
        val userId = UUID.randomUUID().toString()
        val categoriesResponse = List(5) { getFakeUserCategoryResponse() }
        coEvery { api.getUserCategories(userId = userId) } returns responseSuccess(categoriesResponse)

        val result = sut.getUserCategories(userId = userId)

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }
        val successResult = result as Result.Success
        assertThat(successResult.data).apply {
            isNotEmpty()
            hasSize(categoriesResponse.size)
            isEqualTo(categoriesResponse.map { it.toCategoryInfo() })
        }
    }

    @Test
    fun `Get empty user categories if api code 404`() = runTest {
        coEvery { api.getUserCategories(any()) } returns emptyResponseError(404)

        val result = sut.getUserCategories(userId = UUID.randomUUID().toString())

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }
        val successResult = result as Result.Success
        assertThat(successResult.data).apply {
            isEmpty()
        }

    }

    @Test
    fun `Getting empty subscribed events when 404 API status code`() = runTest {
        coEvery { api.getUserSubscribedEvents(any()) } returns emptyResponseError(404)

        val result = sut.getUserSubscribedEvents(userId = UUID.randomUUID().toString())

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }
        val successResult = result as Result.Success
        assertThat(successResult.data).apply {
            isInstanceOf(List::class.java)
            isEmpty()
        }
    }



//    @Test
//    fun changeUser() {
//    }
//
//    @Test
//    fun getUserInfo() {
//    }
//
//    @Test
//    fun getUserCategories() {
//    }
//
//    @Test
//    fun setUserCategories() {
//    }
//
//    @Test
//    fun getUserSubscribedEvents() {
//    }
//
//    @Test
//    fun deleteUser() {
//    }
//

    private fun getFakeUserInfoResponse() = UserInfoResponse(
        id = UUID.randomUUID().toString(),
        firstName = lorem.word(),
        lastName = lorem.word(),
        middleName = lorem.word(),
        email = lorem.word(),
        telegramName = lorem.word()
    )
    private fun getFakeUserCategoryResponse() = CategoryInfoResponse(
        id = UUID.randomUUID().toString(),
        title = lorem.word(),
        color = Random.nextHex(),
        cover = ""
    )
}
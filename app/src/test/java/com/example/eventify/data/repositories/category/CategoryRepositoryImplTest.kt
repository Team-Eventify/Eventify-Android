package com.example.eventify.data.repositories.category

import com.example.eventify.data.remote.api.CategoryAPI
import com.example.eventify.data.remote.models.category.CategoryInfoResponse
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.Category
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
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.random.Random


@ExtendWith(MockKExtension::class)
class CategoryRepositoryImplTest {
    @MockK
    private lateinit var api: CategoryAPI
    private lateinit var sut: CategoryRepositoryImpl

    private val faker = Faker()
    private val lorem = faker.lorem()

    private fun getFakeCategoryInfoResponse() = CategoryInfoResponse(
        id = UUID.randomUUID().toString(),
        title = lorem.words(Random.nextInt(1, 3)).joinToString(),
        color = Random.nextHex(),
        cover = UUID.randomUUID().toString()
    )

    @BeforeEach
    fun setUp() {
        sut = CategoryRepositoryImpl(api)
    }

    @Test
    fun `Getting list of categories`() = runTest {
        val categoriesResponseBody = List(10) {
            getFakeCategoryInfoResponse()
        }
        coEvery { api.getCategoriesList() } returns responseSuccess(code = 200, body = categoriesResponseBody)

        val result = sut.getCategoriesList()

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }
        val successResult = result as Result.Success
        assertThat(successResult.data).apply {
            isEqualTo(categoriesResponseBody.map { it.toCategoryInfo() })
        }
    }

    @Test
    fun `Empty categories list with 404 API status code`() = runTest {
        coEvery { api.getCategoriesList() } returns emptyResponseError(404)

        val result = sut.getCategoriesList()

        assertThat(result).apply {
            isInstanceOf(Result.Success::class.java)
        }
        val successResult = result as Result.Success
        assertThat(successResult.data).apply {
            isEqualTo(emptyList<Category>())
        }
    }

    @Test
    fun readCategory() {
    }

    @Test
    fun createCategory() {
    }
}
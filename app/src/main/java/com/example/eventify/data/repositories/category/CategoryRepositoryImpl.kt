package com.example.eventify.data.repositories.category

import com.example.eventify.data.exceptions.CategoryNotFoundException
import com.example.eventify.data.exceptions.NullableResponseException
import com.example.eventify.data.exceptions.UnprocessedServerResponseException
import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.remote.api.CategoryAPI
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: CategoryAPI
) : CategoryRepository {
    override suspend fun getCategoriesList(): List<CategoryInfo> {
        val response = dataSource.getCategoriesList()

        val categories = when (response.code()){
            200 -> {
                response.body()?.map { category ->
                    category.toCategoryInfo()
                }
            }
            404 -> emptyList()
            else -> throw UnprocessedServerResponseException()
        }
        return categories ?: throw NullableResponseException()
    }

    override suspend fun readCategory(categoryId: String): CategoryInfo {
        val response = dataSource.readCategory(categoryId = categoryId)

        val category = when (response.code()){
            200 -> response.body()?.toCategoryInfo()
            404 -> throw CategoryNotFoundException()
            else -> throw UnprocessedServerResponseException()
        }
        return category ?: throw NullableResponseException()
    }

    override suspend fun createCategory() {
        TODO("Not yet implemented")
    }
}
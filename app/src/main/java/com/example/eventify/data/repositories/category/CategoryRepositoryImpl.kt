package com.example.eventify.data.repositories.category

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
            else -> throw Exception("Ошибка сервера")
        }
        return categories ?: throw Exception("Ошибка")
    }

    override suspend fun readCategory(categoryId: String): CategoryInfo {
        val response = dataSource.readCategory(categoryId = categoryId)

        val category = when (response.code()){
            200 -> response.body()?.toCategoryInfo()
            else -> throw Exception("Ошибка сервера: code=${response.code()} message=${response.message()}")
        }
        return category ?: throw Exception("Ошибка сервера.")
    }

    override suspend fun createCategory() {
        TODO("Not yet implemented")
    }
}
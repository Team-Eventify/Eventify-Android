package com.example.eventify.data.repositories.category

import com.example.eventify.domain.models.Category
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface CategoryRepository {
    suspend fun getCategoriesList(): Result<List<Category>, DataError>
    suspend fun readCategory(categoryId: String): Result<Category, DataError>
    suspend fun createCategory()
}
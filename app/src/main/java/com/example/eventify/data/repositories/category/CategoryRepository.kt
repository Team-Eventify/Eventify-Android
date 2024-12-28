package com.example.eventify.data.repositories.category

import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface CategoryRepository {
    suspend fun getCategoriesList(): Result<List<CategoryInfo>, DataError>
    suspend fun readCategory(categoryId: String): Result<CategoryInfo, DataError>
    suspend fun createCategory()
}
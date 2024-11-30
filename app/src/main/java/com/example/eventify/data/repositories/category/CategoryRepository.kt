package com.example.eventify.data.repositories.category

import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.remote.models.events.EventsFilterData

interface CategoryRepository {
    suspend fun getCategoriesList(): List<CategoryInfo>
    suspend fun readCategory(categoryId: String): CategoryInfo
    suspend fun createCategory()
}
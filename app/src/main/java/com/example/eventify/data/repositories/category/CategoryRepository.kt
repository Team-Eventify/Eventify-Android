package com.example.eventify.data.repositories.category

import com.example.eventify.data.models.CategoryInfo

interface CategoryRepository {
    suspend fun getCategoriesList(): List<CategoryInfo>
    suspend fun readCategory(categoryId: String): CategoryInfo
    suspend fun createCategory()
}
package com.example.eventify.data.repositories.category

import com.example.eventify.domain.models.Category

interface CategoryRepository {
    suspend fun getCategoriesList(): List<Category>
    suspend fun readCategory(categoryId: String): Category
    suspend fun createCategory()
}
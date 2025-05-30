package data.repositories.category

import data.models.Category


interface CategoryRepository {
    suspend fun getCategoriesList(): List<Category>
    suspend fun readCategory(categoryId: String): Category
    suspend fun createCategory()
}
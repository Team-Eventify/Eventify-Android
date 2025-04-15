package com.example.eventify.data.repositories.category

import com.example.eventify.domain.models.Category
import com.example.eventify.data.remote.api.CategoryAPI
import com.example.eventify.data.remote.utils.handle
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: CategoryAPI
) : CategoryRepository {
    override suspend fun getCategoriesList(): List<Category> {
        return dataSource.getCategoriesList().handle{
            process(404){
                emptyList()
            }

            transformSuccess { body ->
                body.map { it.toCategoryInfo() }
            }
        }


    }

    override suspend fun readCategory(categoryId: String): Category  {
        return dataSource.readCategory(categoryId = categoryId).handle {
            transformSuccess { it.toCategoryInfo() }
        }
    }

    override suspend fun createCategory() {
        TODO("Not yet implemented")
    }
}
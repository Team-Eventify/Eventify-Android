package com.example.eventify.data.repositories.category

import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.remote.api.CategoryAPI
import com.example.eventify.data.remote.utils.handle
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import timber.log.Timber
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: CategoryAPI
) : CategoryRepository {
    override suspend fun getCategoriesList(): Result<List<CategoryInfo>, DataError> = try {
        dataSource.getCategoriesList().handle { response ->
            response.map { it.toCategoryInfo() }
        }

    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun readCategory(categoryId: String): Result<CategoryInfo, DataError> = try {
        dataSource.readCategory(categoryId = categoryId).handle { response ->
            response.toCategoryInfo()
        }
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun createCategory() {
        TODO("Not yet implemented")
    }
}
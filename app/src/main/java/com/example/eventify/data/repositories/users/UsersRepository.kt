package com.example.eventify.data.repositories.users

import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.remote.api.CategorySlug
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface UsersRepository {
    suspend fun changeUser(userId: String, user: UserChange): Result<UserInfo, DataError>
    suspend fun getUserInfo(userId: String): Result<UserInfo, DataError>
    suspend fun getUserCategories(userId: String): Result<List<CategoryInfo>, DataError>
    suspend fun getUserSubscribedEvents(userId: String): Result<List<EventInfo>, DataError>
    suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Result<Unit, DataError>
    suspend fun deleteUser(userId: String): Result<Unit, DataError>
}
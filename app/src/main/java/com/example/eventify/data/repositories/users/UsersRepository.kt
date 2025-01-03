package com.example.eventify.data.repositories.users

import com.example.eventify.domain.models.Category
import com.example.eventify.domain.models.Event
import com.example.eventify.domain.models.UserChange
import com.example.eventify.domain.models.User
import com.example.eventify.data.remote.api.CategorySlug
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result

interface UsersRepository {
    suspend fun changeUser(userId: String, user: UserChange): Result<User, DataError>
    suspend fun getUserInfo(userId: String): Result<User, DataError>
    suspend fun getUserCategories(userId: String): Result<List<Category>, DataError>
    suspend fun getUserSubscribedEvents(userId: String): Result<List<Event>, DataError>
    suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Result<Unit, DataError>
    suspend fun deleteUser(userId: String): Result<Unit, DataError>
}
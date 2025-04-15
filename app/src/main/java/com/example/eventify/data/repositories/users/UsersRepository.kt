package com.example.eventify.data.repositories.users

import com.example.eventify.domain.models.Category
import com.example.eventify.domain.models.Event
import com.example.eventify.domain.models.UserChange
import com.example.eventify.domain.models.User
import com.example.eventify.data.remote.api.CategorySlug

interface UsersRepository {
    suspend fun changeUser(userId: String, user: UserChange): User
    suspend fun getUserInfo(userId: String): User
    suspend fun getUserCategories(userId: String): List<Category>
    suspend fun getUserSubscribedEvents(userId: String): List<Event>
    suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Unit
    suspend fun deleteUser(userId: String): Unit
}
package com.example.eventify.data.repositories.users

import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.remote.api.CategorySlug

interface UsersRepository {
    suspend fun changeUser(userId: String, user: UserChange): UserInfo
    suspend fun getUserInfo(userId: String): UserInfo
    suspend fun getUserCategories(userId: String): List<CategoryInfo>
    suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Unit
    suspend fun getUserSubscribedEvents(userId: String): List<EventInfo>
}
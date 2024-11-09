package com.example.eventify.data.repositories.users

import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo

interface UsersRepository {
    suspend fun changeUser(userId: String, user: UserChange): UserInfo
    suspend fun getUserInfo(userId: String): UserInfo
    suspend fun getUserCategories(userId: String)
    suspend fun setUserCategories(userId: String)
}
package com.example.eventify.data.repositories.users

import com.example.eventify.data.exceptions.EmptyResponseException
import com.example.eventify.data.exceptions.UnprocessedServerResponseException
import com.example.eventify.data.exceptions.UserNotFoundException
import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.remote.api.CategorySlug
import com.example.eventify.data.remote.api.UsersAPI
import com.example.eventify.data.remote.models.users.ChangeUserRequest
import com.example.eventify.data.remote.models.users.toUserInfo
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    val dataSource: UsersAPI
): UsersRepository {
    override suspend fun changeUser(userId: String, user: UserChange): UserInfo {
        val response = dataSource.changeUser(
            userId = userId,
            user = ChangeUserRequest(
                firstName = user.firstName,
                lastName = user.lastName,
                middleName = user.middleName,
                telegramName = user.telegramName
            )
        )

        val updatedUser = when (response.code()){
            200 -> response.body()?.toUserInfo()
            404 -> throw UserNotFoundException()
            else -> throw UnprocessedServerResponseException()
        }

        return updatedUser ?: throw EmptyResponseException()
    }

    override suspend fun getUserInfo(userId: String): UserInfo {
        val response = dataSource.getUserInfo(userId = userId)
        val user = when (response.code()){
            200 -> response.body()?.toUserInfo()
            404 -> throw UserNotFoundException()
            else -> throw UnprocessedServerResponseException()
        }
        return user ?: throw EmptyResponseException()
    }

    override suspend fun getUserCategories(userId: String): List<CategoryInfo> {
        val response = dataSource.getUserCategories(userId = userId)
        val categories = when (response.code()){
            200 -> response.body()?.map { it.toCategoryInfo() }
            404 -> emptyList()
            else -> throw UnprocessedServerResponseException()
        }
        return categories ?: throw EmptyResponseException()
    }

    override suspend fun setUserCategories(userId: String, categories: List<CategorySlug>) {
        val response = dataSource.setUserCategories(userId = userId, categories = categories)
    }
}
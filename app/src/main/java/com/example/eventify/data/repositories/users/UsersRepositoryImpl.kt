package com.example.eventify.data.repositories.users

import com.example.eventify.data.errors.UserNotFoundException
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
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
            else -> null
        }

        return updatedUser ?: throw Exception("Ошибка сервера.")
    }

    override suspend fun getUserInfo(userId: String): UserInfo {
        val response = dataSource.getUserInfo(userId = userId)
        val user = when (response.code()){
            200 -> response.body()?.toUserInfo()
            404 -> throw UserNotFoundException()
            else -> null
        }
        return user ?: throw Exception("Ошибка сервера.")
    }

    override suspend fun getUserCategories(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserCategories(userId: String) {
        TODO("Not yet implemented")
    }
}
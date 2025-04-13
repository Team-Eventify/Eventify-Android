package com.example.eventify.data.repositories.users

import com.example.eventify.domain.models.Category
import com.example.eventify.domain.models.Event
import com.example.eventify.domain.models.UserChange
import com.example.eventify.domain.models.User
import com.example.eventify.data.remote.api.CategorySlug
import com.example.eventify.data.remote.api.UsersAPI
import com.example.eventify.data.remote.models.events.toEventInfo
import com.example.eventify.data.remote.models.users.ChangeUserRequest
import com.example.eventify.data.remote.models.users.toUserInfo
import com.example.eventify.data.remote.utils.handle
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    val dataSource: UsersAPI
): UsersRepository {
    override suspend fun changeUser(userId: String, user: UserChange): User {
        return dataSource.changeUser(
            userId = userId,
            user = ChangeUserRequest(
                firstName = user.firstName,
                lastName = user.lastName,
                telegramName = user.telegramName,
                email = user.email
            )
        ).handle{
            transformSuccess { it.toUserInfo() }
        }
    }

    override suspend fun getUserInfo(userId: String): User {
        return dataSource.getUserInfo(userId = userId).handle{
            transformSuccess { it.toUserInfo() }
        }
    }

    override suspend fun getUserCategories(userId: String): List<Category> {
        return dataSource.getUserCategories(userId = userId).handle{
            transformSuccess { body ->
                body.map { it.toCategoryInfo() }
            }
            process(404){
                emptyList()
            }
        }
    }

    override suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Unit {
        return dataSource.setUserCategories(userId = userId, categories = categories).handle()
    }

    override suspend fun getUserSubscribedEvents(userId: String): List<Event> {
        return dataSource.getUserSubscribedEvents(userId = userId).handle{
            transformSuccess { body ->
                body.map { it.toEventInfo() }
            }
            process(404){
                emptyList()
            }
        }
    }

    override suspend fun deleteUser(userId: String): Unit {
        return dataSource.deleteUserById(userId = userId).handle()
    }
}
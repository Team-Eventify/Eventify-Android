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
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import timber.log.Timber
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    val dataSource: UsersAPI
): UsersRepository {
    override suspend fun changeUser(userId: String, user: UserChange): Result<User, DataError> = try {
        dataSource.changeUser(
            userId = userId,
            user = ChangeUserRequest(
                firstName = user.firstName,
                lastName = user.lastName,
                middleName = user.middleName,
                telegramName = user.telegramName,
                email = user.email
            )
        ).handle{
            transformSuccess { it.toUserInfo() }
        }

    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun getUserInfo(userId: String): Result<User, DataError> = try {
        dataSource.getUserInfo(userId = userId).handle{
            transformSuccess { it.toUserInfo() }
        }
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun getUserCategories(userId: String): Result<List<Category>, DataError> = try {
        dataSource.getUserCategories(userId = userId).handle{
            transformSuccess { body ->
                body.map { it.toCategoryInfo() }
            }
            process(404){
                Result.Success(emptyList())
            }
        }
    } catch (e: Exception){
        Timber.d(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Result<Unit, DataError> = try {
        dataSource.setUserCategories(userId = userId, categories = categories).handle()
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun getUserSubscribedEvents(userId: String): Result<List<Event>, DataError> = try {
        dataSource.getUserSubscribedEvents(userId = userId).handle{
            transformSuccess { body ->
                body.map { it.toEventInfo() }
            }
        }
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun deleteUser(userId: String): Result<Unit, DataError> = try {
        dataSource.deleteUserById(userId = userId).handle()
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }
}
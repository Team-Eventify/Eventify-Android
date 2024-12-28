package com.example.eventify.data.repositories.users

import android.provider.ContactsContract.Data
import com.example.eventify.data.exceptions.AccessDeniedException
import com.example.eventify.data.exceptions.CategoryNotFoundException
import com.example.eventify.data.exceptions.NullableResponseException
import com.example.eventify.data.exceptions.UnprocessedServerResponseException
import com.example.eventify.data.exceptions.UserNotFoundException
import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
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
    override suspend fun changeUser(userId: String, user: UserChange): Result<UserInfo, DataError> = try {
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
            it.toUserInfo()
        }

    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun getUserInfo(userId: String): Result<UserInfo, DataError> = try {
        dataSource.getUserInfo(userId = userId).handle{
            it.toUserInfo()
        }
    } catch (e: Exception){
        Timber.e(e)
        Result.Error(DataError.Network.UNKNOWN)
    }

    override suspend fun getUserCategories(userId: String): Result<List<CategoryInfo>, DataError> = try {
        dataSource.getUserCategories(userId = userId).handle{ response ->
            response.map { it.toCategoryInfo() }
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

    override suspend fun getUserSubscribedEvents(userId: String): Result<List<EventInfo>, DataError> = try {
        dataSource.getUserSubscribedEvents(userId = userId).handle{ response ->
            response.map { it.toEventInfo() }
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
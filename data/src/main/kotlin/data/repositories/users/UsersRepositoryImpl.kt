package data.repositories.users

import data.models.Category
import data.models.Event
import data.models.User
import data.models.UserChange
import javax.inject.Inject
import data.remote.api.UsersAPI
import data.remote.models.users.ChangeUserRequest
import data.remote.utils.handle

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
package data.repositories.users

import data.models.Category
import data.models.Event
import data.models.User
import data.models.UserChange
import data.remote.api.CategorySlug


interface UsersRepository {
    suspend fun changeUser(userId: String, user: UserChange): User
    suspend fun getUserInfo(userId: String): User
    suspend fun getUserCategories(userId: String): List<Category>
    suspend fun getUserSubscribedEvents(userId: String): List<Event>
    suspend fun setUserCategories(userId: String, categories: List<CategorySlug>): Unit
    suspend fun deleteUser(userId: String): Unit
}
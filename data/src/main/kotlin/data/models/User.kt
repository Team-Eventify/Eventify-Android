package data.models

import data.remote.models.users.UserInfoResponse

data class User(
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val telegramName: String
)

internal fun UserInfoResponse.toBusiness(): User = User(
    email = this.email,
    firstName = this.firstName ?: "",
    id = this.id,
    lastName = this.lastName ?: "",
    telegramName = this.telegramName ?: "",
)
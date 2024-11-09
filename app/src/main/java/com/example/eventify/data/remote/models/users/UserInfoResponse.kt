package com.example.eventify.data.remote.models.users

import com.example.eventify.data.models.UserInfo

data class UserInfoResponse(
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val middleName: String,
    val telegramName: String
)


fun UserInfoResponse.toUserInfo(): UserInfo = UserInfo(
    email = this.email,
    firstName = this.firstName,
    id = this.id,
    lastName = this.lastName,
    middleName = this.middleName,
    telegramName = this.telegramName,
)

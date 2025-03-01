package com.example.eventify.data.remote.models.users

data class ChangeUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val telegramName: String
)
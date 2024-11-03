package com.example.eventify.data.remote.models.auth

data class LogInResponse(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)
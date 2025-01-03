package com.example.eventify.domain.models

data class TokenData(
    val accessToken: String,
    val refreshToken: String,
    val userID: String
)

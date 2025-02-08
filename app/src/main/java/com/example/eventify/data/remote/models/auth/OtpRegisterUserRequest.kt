package com.example.eventify.data.remote.models.auth

data class OtpRegisterUserRequest(
    val email: String,
    val password: String,
    val code: String
)
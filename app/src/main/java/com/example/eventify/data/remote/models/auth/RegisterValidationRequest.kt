package com.example.eventify.data.remote.models.auth

data class RegisterValidationRequest(
    val email: String,
    val password: String
)
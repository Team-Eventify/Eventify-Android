package com.example.eventify.domain.models

data class OtpUserCreate(
    val email: String,
    val password: String,
    val code: String,
    val validationResultId: String,
)

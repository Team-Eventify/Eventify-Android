package com.example.eventify.domain.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)

package com.example.eventify.domain.validation

interface Validator<T> {
    operator fun invoke(value: T): ValidationResult
}
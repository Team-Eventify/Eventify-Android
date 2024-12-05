package com.example.eventify.domain.validation

import android.util.Patterns

class ValidateEmail : Validator<String> {
    override fun invoke(value: String): ValidationResult {
        if (value.isBlank())
            return ValidationResult(successful = false, errorMessage = "Email can't be blank")

        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches())
            return ValidationResult(successful = false, errorMessage = "That's not a valid email")

        return ValidationResult(successful = true)
    }
}
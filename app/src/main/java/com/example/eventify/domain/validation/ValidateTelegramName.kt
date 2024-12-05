package com.example.eventify.domain.validation

class ValidateTelegramName : Validator<String> {
    override fun invoke(value: String): ValidationResult {
        if (value.trim().any { it.isWhitespace() })
            return ValidationResult(successful = false, errorMessage = "Telegram name can't contains whitespace")

        return ValidationResult(successful = true)
    }

}
package com.example.eventify.domain.validation

class ValidatePassword: Validator<String> {
    override fun invoke(value: String): ValidationResult {
        if (value.isBlank())
            return ValidationResult(successful = false, errorMessage = "Password cant be blank")

        if (!value.any { it.isUpperCase() })
            return ValidationResult(successful = false, errorMessage = "Password must contains uppercase")

        if (!value.any {it.isDigit()})
            return ValidationResult(successful = false, errorMessage = "Password must contains digits")

        if (value.length < MIN_CHARACTERS)
            return ValidationResult(successful = false, errorMessage = "Password must be longer than 8")

        return ValidationResult(successful = true)
    }

    companion object{
        const val MIN_CHARACTERS = 8
    }
}
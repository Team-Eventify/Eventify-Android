package com.example.eventify.domain.validation

import com.example.eventify.domain.Error
import com.example.eventify.domain.Result

class ValidatePassword: Validator<String, PasswordValidationError> {
    override fun invoke(value: String): Result<Unit, PasswordValidationError> {
        if (value.isBlank())
            return Result.Error(PasswordValidationError.IS_EMPTY)

        if (!value.any { it.isUpperCase() })
            return Result.Error(PasswordValidationError.NO_UPPERCASE)

        if (!value.any {it.isDigit()})
            return Result.Error(PasswordValidationError.NO_DIGITS)

        if (value.length < MIN_CHARACTERS)
            return Result.Error(PasswordValidationError.TOO_SHORT)

        return Result.Success(Unit)
    }

    companion object{
        const val MIN_CHARACTERS = 8
    }
}

enum class PasswordValidationError: Error{
    IS_EMPTY,
    NO_DIGITS,
    NO_UPPERCASE,
    TOO_SHORT
}
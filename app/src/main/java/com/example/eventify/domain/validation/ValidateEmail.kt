package com.example.eventify.domain.validation

import android.util.Patterns
import com.example.eventify.domain.Error
import com.example.eventify.domain.Result

class ValidateEmail : Validator<String, EmailValidationError> {
    override fun invoke(value: String): Result<Unit, EmailValidationError> {
        if (value.isBlank())
            return Result.Error(EmailValidationError.IS_EMPTY)

        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches())
            return Result.Error(EmailValidationError.INCORRECT_PATTERN)

        return Result.Success(Unit)
    }
}


enum class EmailValidationError: Error{
    INCORRECT_PATTERN,
    IS_EMPTY
}
package com.example.eventify.domain.validation

import com.example.eventify.domain.Error
import com.example.eventify.domain.Result

class ValidateTelegramName : Validator<String, TelegramNameValidationError> {
    override fun invoke(value: String): Result<String, TelegramNameValidationError> {
        if (value.trim().any { it.isWhitespace() })
            return Result.Error(TelegramNameValidationError.HAS_WHITE_SPACE)

        return Result.Success(value)
    }

}

enum class TelegramNameValidationError: Error{
    HAS_WHITE_SPACE
}
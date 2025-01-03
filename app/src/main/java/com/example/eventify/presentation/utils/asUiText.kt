package com.example.eventify.presentation.utils

import com.example.eventify.R
import com.example.eventify.domain.DataError
import com.example.eventify.domain.validation.EmailValidationError
import com.example.eventify.domain.validation.PasswordValidationError
import com.example.eventify.domain.validation.TelegramNameValidationError
import com.example.eventify.domain.validation.ValidatePassword

fun EmailValidationError.asUiText(): UiText = when (this){
    EmailValidationError.INCORRECT_PATTERN -> UiText.StringResource(
        R.string.incorrect_email
    )
    EmailValidationError.IS_EMPTY -> UiText.StringResource(
        R.string.this_field_must_be_filled
    )
}

fun PasswordValidationError.asUiText(): UiText = when (this){
    PasswordValidationError.NO_DIGITS -> UiText.StringResource(
        R.string.password_no_digits_error
    )
    PasswordValidationError.IS_EMPTY -> UiText.StringResource(
        R.string.this_field_must_be_filled
    )
    PasswordValidationError.NO_UPPERCASE -> UiText.StringResource(
        R.string.password_no_uppercase_error
    )
    PasswordValidationError.TOO_SHORT -> UiText.StringResource(
        R.string.password_to_short_error,
        arrayOf(ValidatePassword.MIN_CHARACTERS)
    )
}


fun TelegramNameValidationError.asUiText(): UiText = when (this){
    TelegramNameValidationError.HAS_WHITE_SPACE -> UiText.StringResource(
        R.string.this_field_must_not_contain_spaces
    )
}

fun DataError.asUiText(): UiText = when (this){
    is DataError.Network -> {
        when (this){
            DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
                R.string.request_timeout
            )
            DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
                R.string.too_many_requests
            )
            DataError.Network.NO_INTERNET -> UiText.StringResource(
                R.string.no_internet
            )
            DataError.Network.SERVER_ERROR -> UiText.StringResource(
                R.string.server_error
            )
            DataError.Network.UNKNOWN -> UiText.StringResource(
                R.string.unknown_error
            )
            DataError.Network.NOT_FOUND -> UiText.StringResource(
                R.string.not_found
            )
            DataError.Network.BAD_REQUEST -> UiText.StringResource(
                R.string.bad_request
            )
            DataError.Network.FORBIDDEN -> UiText.StringResource(
                R.string.forbidden
            )
            DataError.Network.UNAUTHORIZED -> UiText.StringResource(
                R.string.unauthorized_error
            )
            else -> UiText.DynamicString(this.toString())
        }
    }
    is DataError.Local -> {
        when (this){
            DataError.Local.DISK_FULL -> UiText.StringResource(
                R.string.disk_full
            )
        }
    }

    else -> UiText.DynamicString(this.toString())
}



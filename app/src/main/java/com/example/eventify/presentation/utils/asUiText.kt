package com.example.eventify.presentation.utils

import com.example.eventify.R
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



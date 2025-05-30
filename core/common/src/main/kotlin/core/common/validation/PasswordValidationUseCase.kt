package core.common.validation

private const val MIN_PASSWORD_LENGTH = 8


enum class PasswordValidationError : ValidationError {
    TooShort,
}


class PasswordValidationUseCase : ValidationUseCase<String, PasswordValidationError> {
    override fun invoke(value: String): ValidationResult<String, PasswordValidationError> {
        if (value.length < MIN_PASSWORD_LENGTH)
            return ValidationResult(
                isValid = false,
                error = PasswordValidationError.TooShort
            )

        return ValidationResult(isValid = true, value)
    }
}
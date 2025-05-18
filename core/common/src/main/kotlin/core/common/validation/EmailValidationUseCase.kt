package core.common.validation

import core.common.extentions.isValidEmail


enum class EmailValidationError : ValidationError {
    IncorrectEmail
}

data class ValidationResult<T, E: ValidationError>(
    val isValid: Boolean,
    val value: T? = null,
    val error: E? = null,
)



class EmailValidationUseCase : ValidationUseCase<String, EmailValidationError> {
    override operator fun invoke(value: String): ValidationResult<String, EmailValidationError> {
        if (!value.isValidEmail())
            return ValidationResult(
                isValid = false,
                error = EmailValidationError.IncorrectEmail,
            )

        return ValidationResult(isValid = true, value = value)
    }
}

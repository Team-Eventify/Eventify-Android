package core.common.validation

enum class TelegramNameValidationError : ValidationError {

}


class TelegramNameValidationUseCase : ValidationUseCase<String, TelegramNameValidationError> {
    override fun invoke(value: String): ValidationResult<String, TelegramNameValidationError> {
        return ValidationResult(isValid = true, value = value)
    }
}
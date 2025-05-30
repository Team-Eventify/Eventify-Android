package core.common.extentions

import android.content.Context
import android.util.Patterns
import core.common.validation.EmailValidationError
import core.common.validation.PasswordValidationError
import core.common.validation.ValidationError
import core.common.validation.ValidationResult
import com.example.eventify.core.common.R as CommonR

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun <T, E: ValidationError> ValidationResult<T, E>.onError(block: (E) -> Unit): T? {
    if (this.isValid) return this.value
    this.error?.let { block(it) }
    return null
}


fun ValidationError.asText(context: Context): String {
    return when (this) {
        PasswordValidationError.TooShort -> context.getString(CommonR.string.minimum_password_length_error)
        EmailValidationError.IncorrectEmail -> context.getString(CommonR.string.invalid_email)
    }
}
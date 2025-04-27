package com.example.eventify.domain.validation

import android.util.Patterns

interface ValidationField {
    val isValid: Boolean
        get() = try {
            validate()
            true
        } catch (e: ValidationException) {
            false
        }

    fun validate(): Unit
}

open class ValidationException : Exception("Validation Error")

class TooShortException(val minLength: Int) : ValidationException()

class InvalidEmailException : ValidationException()

class MissingRequireSizeExceptions(val requireSize: Int) : ValidationException()



@JvmInline
value class Email(val value: String = "") : ValidationField {

    override fun validate() {
        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) throw InvalidEmailException()
    }
}

@JvmInline
value class Password(val value: String = "") : ValidationField {

    override fun validate() {
        if (value.length < 8) throw TooShortException(minLength = 8)
    }
}

@JvmInline
value class OTP(val value: String = "") : ValidationField {

    override fun validate() {
        if (value.length != 6) throw MissingRequireSizeExceptions(requireSize = 6)
    }
}

fun String.asPassword() = Password(this)

fun String.asEmail() = Email(this)

fun String.asOTP() = OTP(this)



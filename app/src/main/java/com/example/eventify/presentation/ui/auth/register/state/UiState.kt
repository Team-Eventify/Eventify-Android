package com.example.eventify.presentation.ui.auth.register.state

import androidx.compose.runtime.Stable
import com.example.eventify.presentation.utils.UiText


const val OTP_LENGTH = 6


@Stable
data class RegisterState(
    val login: String = "",
    val hasLoginError: Boolean = false,
    val loginError: UiText? = null,

    val password: String = "",
    val hasPasswordError: Boolean = false,
    val passwordError: UiText? = null,

    val otp: String? = null,
    val otpError: Boolean = false,
    val showOtpBottomSheet: Boolean = false,
){
    val isValidLogin: Boolean
        get() = login.isNotEmpty()

    val isValidPassword: Boolean
        get() = password.isNotEmpty()

    val isValidFormData: Boolean
        get() = isValidLogin && isValidPassword

    val isValidOtp: Boolean
        get() = otp?.let { it.length == OTP_LENGTH } ?: false
}

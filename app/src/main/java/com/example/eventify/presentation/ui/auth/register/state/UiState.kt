package com.example.eventify.presentation.ui.auth.register.state

import androidx.compose.runtime.Stable
import com.example.eventify.domain.validation.Email
import com.example.eventify.domain.validation.OTP
import com.example.eventify.domain.validation.Password




@Stable
data class RegisterUiState(
    val payloadState: RegisterPayloadState = RegisterPayloadState(),
    val otpState: OtpState = OtpState.None
) {
    val isOtpRequested: Boolean
        get() = otpState is OtpState.Requested
}


data class RegisterPayloadState(
    val login: Email = Email(),
    val hasLoginError: Boolean = false,
    val loginError: String? = null,

    val password: Password = Password(),
    val hasPasswordError: Boolean = false,
    val passwordError: String? = null,
) {
    val hasErrors: Boolean
        get() = hasLoginError && hasPasswordError

}


sealed class OtpState {
    data object None : OtpState()
    data object Requested : OtpState()
    data class ShowOtp(
        val otp: OTP = OTP(),
        val hasError: Boolean = false,
        val errorMessage: String? = null,
    ) : OtpState()
}
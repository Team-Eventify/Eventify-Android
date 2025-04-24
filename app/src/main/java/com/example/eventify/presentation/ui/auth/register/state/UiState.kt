package com.example.eventify.presentation.ui.auth.register.state

import androidx.compose.runtime.Stable


const val OTP_LENGTH = 6


@Stable
data class RegisterUiState(
    val payloadState: RegisterPayloadState = RegisterPayloadState(),
    val otpState: OtpState = OtpState.None
) {
    val isOtpRequested: Boolean
        get() = otpState is OtpState.Requested
}


data class RegisterPayloadState(
    val login: String = "",
    val hasLoginError: Boolean = false,
    val loginError: String? = null,

    val password: String = "",
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
        val value: String = "",
        val hasError: Boolean = false,
    ) : OtpState()
}
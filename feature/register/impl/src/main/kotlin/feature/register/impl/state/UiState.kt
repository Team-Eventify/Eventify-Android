package feature.register.impl.state

import androidx.compose.runtime.Stable




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
        val otp: String = "",
        val hasError: Boolean = false,
        val errorMessage: String? = null,
        val isSuccess: Boolean = false,
    ) : OtpState()
}
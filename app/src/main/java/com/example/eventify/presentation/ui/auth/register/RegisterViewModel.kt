package com.example.eventify.presentation.ui.auth.register

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.models.OtpUserCreate
import com.example.eventify.domain.models.RegisterValidationData
import com.example.eventify.domain.usecases.auth.OtpRegisterUseCase
import com.example.eventify.domain.usecases.auth.isIncorrectOtp
import com.example.eventify.domain.validation.Email
import com.example.eventify.domain.validation.OTP
import com.example.eventify.domain.validation.Password
import com.example.eventify.presentation.ui.auth.register.state.OtpState
import com.example.eventify.presentation.ui.auth.register.state.RegisterPayloadState
import com.example.eventify.presentation.ui.auth.register.state.RegisterUiState
import com.example.eventify.presentation.ui.auth.register.state.SideEffect
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val otpRegisterUseCase: OtpRegisterUseCase,
    private val authRepository: AuthUserRepository,
    @ApplicationContext private val context: Context,
) : BaseViewModel() {

    private var validationResultId = MutableStateFlow<String?>(null)

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val mutableOtpState = MutableStateFlow<OtpState>(OtpState.None)
    private val mutableRegisterPayloadState = MutableStateFlow(RegisterPayloadState())

    val state = combine(
        mutableRegisterPayloadState,
        mutableOtpState,
    ){ payload, otp ->
        RegisterUiState(
            payloadState = payload,
            otpState = otp,
        )
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RegisterUiState()
        )


    fun changeLogin(value: Email){
        mutableRegisterPayloadState.update { currentState ->
            currentState.copy(
                login = value,
                loginError = null,
                hasLoginError = false,
            )
        }
    }

    fun changePassword(value: Password){
        mutableRegisterPayloadState.update { currentState ->
            currentState.copy(
                password = value,
                hasPasswordError = false,
                passwordError = null,
            )
        }
    }

    fun changeOtp(value: OTP){
        mutableOtpState.update { currentState ->
            (currentState as? OtpState.ShowOtp)?.copy(
                otp = value,
                hasError = false,
            ) ?: currentState
        }
    }

    fun register(
        login: String,
        password: String
    ) {
        launchCatching(catch = ::handleRegisterErrors) {
            // TODO needs validation
            val otpData = RegisterValidationData(
                email = login,
                password = password,
            )


            authRepository.validateRegisterData(data = otpData).let { resultId ->
                validationResultId.update { resultId }
            }
            triggerOtpBottomSheet(true)
        }
    }


    fun validateOtp(
        login: String,
        password: String,
        otp: String,
    ){
        launchCatching(catch = ::handleOtpErrors) {
            val validationId = validationResultId.value ?: return@launchCatching

            // TODO needs validation
            val userPayload = OtpUserCreate(
                    email = login,
                    password = password,
                    code = otp,
                    validationResultId = validationId
                )

            otpRegisterUseCase(userData = userPayload)
            mutableSideEffect.send(SideEffect.SuccessRegister)
        }
    }

    fun triggerOtpBottomSheet(value: Boolean){
        mutableOtpState.update {
            OtpState.ShowOtp().takeIf { value } ?: OtpState.None
        }
    }

    /**
     * Handles errors after sending validation request to get otp
     **/
    private fun handleOtpErrors(error: Throwable){

        when {
            error.isIncorrectOtp() -> {
                mutableOtpState.update { currentState ->
                    (currentState as? OtpState.ShowOtp)?.copy(
                        hasError = true,
                        errorMessage = context.getString(R.string.incorrect_otp)
                    ) ?: currentState
                }
            }
            else -> {
                mutableOtpState.update { currentState ->
                    (currentState as? OtpState.ShowOtp)?.copy(
                        hasError = true,
                        errorMessage = context.getString(R.string.server_error)
                    ) ?: currentState
                }
            }
        }
    }

    /**
     * Handles errors after sending register request with otp value
    **/
    private fun handleRegisterErrors(error: Throwable) {
        when {
            else -> {
                mutableRegisterPayloadState.update { currentState ->
                    (currentState as? RegisterPayloadState)?.copy(
                        hasPasswordError = true,
                        hasLoginError = true,
                    ) ?: currentState
                }
                mutableSideEffect.trySend(SideEffect.ServerError)
            }
        }
    }


}
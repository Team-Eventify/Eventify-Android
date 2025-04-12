package com.example.eventify.presentation.ui.auth.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.OtpUserCreate
import com.example.eventify.domain.models.RegisterValidationData
import com.example.eventify.domain.usecases.auth.OtpRegisterUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidatePassword
import com.example.eventify.presentation.ui.auth.register.state.OtpState
import com.example.eventify.presentation.ui.auth.register.state.RegisterPayloadState
import com.example.eventify.presentation.ui.auth.register.state.RegisterUiState
import com.example.eventify.presentation.ui.auth.register.state.SideEffect
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val otpRegisterUseCase: OtpRegisterUseCase,
    private val authRepository: AuthUserRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val validateEmailUseCase = ValidateEmail()
    private val validatePasswordUseCase = ValidatePassword()

    private var validationResultId: String? = null

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


    fun changeLogin(value: String){
        mutableRegisterPayloadState.update { currentState ->
            currentState.copy(
                login = value,
                loginError = null,
                hasLoginError = false,
            )
        }
    }

    fun changePassword(value: String){
        mutableRegisterPayloadState.update { currentState ->
            currentState.copy(
                password = value,
                hasPasswordError = false,
                passwordError = null,
            )
        }
    }

    fun changeOtp(value: String){
        mutableOtpState.update { currentState ->
            (currentState as? OtpState.ShowOtp)?.copy(
                value = value,
                hasError = false,
            ) ?: currentState
        }
    }

    fun requestOtp() {
        val validLogin = validateEmail() ?: return
        val validPassword = validatePassword() ?: return

        val otpData = RegisterValidationData(
                email = validLogin,
                password = validPassword,
            )

        viewModelScope.launch {
            mutableOtpState.update { OtpState.Requested }

            when (val result = authRepository.validateRegisterData(data = otpData)) {
                is Result.Error -> {
                    mutableOtpState.update { OtpState.None }

                    mutableRegisterPayloadState.update { currentState ->
                        currentState.copy(
                            hasPasswordError = false,
                            hasLoginError = false,
                        )
                    }
                    mutableSideEffect.send(SideEffect.FailRegister(
                        message = result.error.asUiText().asString(context)
                    ))
                }
                is Result.Success -> {
                    validationResultId = result.data
                    triggerOtpBottomSheet(true)
                }
            }
        }
    }


    fun register(){
        if (validationResultId == null) {
            return
        }

        val userPayload = (mutableOtpState.value as? OtpState.ShowOtp)?.value?.let { otp ->
            OtpUserCreate(
                email = mutableRegisterPayloadState.value.login,
                password = mutableRegisterPayloadState.value.password,
                code = otp,
                validationResultId = validationResultId!!
            )
        } ?: return


        viewModelScope.launch {
            when (val result = otpRegisterUseCase(userData = userPayload)){
                is Result.Error -> handleOtpErrors(result.error)
                is Result.Success -> {
                    mutableSideEffect.send(SideEffect.SuccessRegister)
                }
            }
        }
    }

    fun triggerOtpBottomSheet(value: Boolean){
        mutableOtpState.update {
            OtpState.ShowOtp().takeIf { value } ?: OtpState.None
        }
    }

    private fun validateEmail(): String? {
        return when (val result = validateEmailUseCase(mutableRegisterPayloadState.value.login)) {
            is Result.Error -> {
                mutableRegisterPayloadState.update {
                    it.copy(
                        hasLoginError = true,
                        loginError = result.error.asUiText()
                    )
                }
                null
            }
            is Result.Success -> result.data
        }
    }

    private fun validatePassword(): String? {
        return when (val result = validatePasswordUseCase(mutableRegisterPayloadState.value.password)) {
            is Result.Error -> {
                mutableRegisterPayloadState.update {
                    it.copy(
                        hasPasswordError = true,
                        passwordError = result.error.asUiText()
                    )
                }
                null
            }
            is Result.Success -> result.data
        }
    }

    private suspend fun handleOtpErrors(error: DataError){
        when (error) {
            is DataError.Network -> {
                when (error) {
                    DataError.Network.BAD_REQUEST -> {
                        mutableOtpState.update { currentState ->
                            (currentState as? OtpState.ShowOtp)?.copy(
                                hasError = true,
                            ) ?: currentState
                        }
                    }
                    else -> {
                        mutableSideEffect.send(
                            SideEffect.FailRegister(
                                message = error.asUiText().asString(context)
                            )
                        )
                    }
                }
            }
            else -> {
                mutableSideEffect.send(
                    SideEffect.FailRegister(
                        message = error.asUiText().asString(context)
                    )
                )
            }
        }
    }

}
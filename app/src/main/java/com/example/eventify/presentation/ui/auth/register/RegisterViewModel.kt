package com.example.eventify.presentation.ui.auth.register

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.models.OtpUserCreate
import com.example.eventify.domain.models.RegisterValidationData
import com.example.eventify.domain.usecases.auth.OtpRegisterUseCase
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

        launchCatching(
            catch = { error ->
                mutableOtpState.update { OtpState.None }

                mutableRegisterPayloadState.update { currentState ->
                    currentState.copy(
                        hasPasswordError = false,
                        hasLoginError = false,
                    )
                }
                mutableSideEffect.trySend(SideEffect.FailRegister(
                    message = error.message
                ))
            }
        ) {
            authRepository.validateRegisterData(data = otpData).let {
                validationResultId = it
            }
            triggerOtpBottomSheet(true)
        }
    }


    fun register(){
        if (validationResultId == null) return

        val userPayload = (mutableOtpState.value as? OtpState.ShowOtp)?.value?.let { otp ->
            OtpUserCreate(
                email = mutableRegisterPayloadState.value.login,
                password = mutableRegisterPayloadState.value.password,
                code = otp,
                validationResultId = validationResultId!!
            )
        } ?: return

        launchCatching(
            catch = ::handleOtpErrors
        ) {
            otpRegisterUseCase(userData = userPayload)
            mutableSideEffect.send(SideEffect.SuccessRegister)
        }
    }

    fun triggerOtpBottomSheet(value: Boolean){
        mutableOtpState.update {
            OtpState.ShowOtp().takeIf { value } ?: OtpState.None
        }
    }

    private fun validateEmail(): String? {
        return mutableRegisterPayloadState.value.login

    }

    private fun validatePassword(): String? {
        return mutableRegisterPayloadState.value.password

    }

    private fun handleOtpErrors(error: Throwable){
        // TODO обработать
    }

}
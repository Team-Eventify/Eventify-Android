package com.example.eventify.presentation.ui.auth.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.repositories.auth.AuthUserRepository
import com.example.eventify.domain.models.UserCreate
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.OtpUserCreate
import com.example.eventify.domain.models.RegisterValidationData
import com.example.eventify.domain.usecases.auth.OtpRegisterUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidatePassword
import com.example.eventify.presentation.ui.auth.register.state.RegisterState
import com.example.eventify.presentation.ui.auth.register.state.SideEffect
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val otpRegisterUseCase: OtpRegisterUseCase,
    private val authRepository: AuthUserRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val validateEmailUseCase: ValidateEmail = ValidateEmail()
    private val validatePasswordUseCase: ValidatePassword = ValidatePassword()

    private var validationResultId: String? = null

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()


    fun changeLogin(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    private fun validateLogin(): Boolean{
        val validationResult = validateEmailUseCase(_stateFlow.value.login)
        val (error, hasError) = when (validationResult){
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            currentState.copy(
                loginError = error,
                hasLoginError = hasError
            )
        }
        return validationResult is Result.Success
    }

    fun changePassword(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    private fun validatePassword(): Boolean{
        val validationResult = validatePasswordUseCase(_stateFlow.value.password)
        val (error, hasError) = when (validationResult){
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            currentState.copy(
                passwordError = error,
                hasPasswordError = hasError
            )
        }
        return validationResult is Result.Success
    }

    fun changeOtp(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                otp = value
            )
        }
    }

    fun requestOtp() {
        val isValidData = listOf(
            validateLogin(),
            validatePassword()
        ).all{ it }

        if (!isValidData) return

        val validationData = _stateFlow.value.run {
            RegisterValidationData(
                email = login,
                password = password,
            )
        }

        viewModelScope.launch {
            when (val result = authRepository.validateRegisterData(data = validationData)) {
                is Result.Error -> {
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            hasPasswordError = true,
                            hasLoginError = true,
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

    fun triggerOtpBottomSheet(value: Boolean){
        _stateFlow.update { currentState -> currentState.copy(showOtpBottomSheet = value) }
    }


    fun register(){
        if (validationResultId == null || _stateFlow.value.otp == null) {
            return
        }

        val userPayload = stateFlow.value.run {
            OtpUserCreate(
                email = login,
                password = password,
                code = otp!!,
                validationResultId = validationResultId!!
            )
        }

        viewModelScope.launch {
            when (val result = otpRegisterUseCase(userData = userPayload)){
                is Result.Error -> handleErrors(result.error)
                is Result.Success -> {
                    triggerOtpBottomSheet(false)
                }
            }
        }
    }
    

    private suspend fun handleErrors(error: DataError){
        mutableSideEffect.send(SideEffect.FailRegister(
            message = error.asUiText().asString(context)
        ))
    }

}
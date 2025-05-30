package feature.register.impl

import android.content.Context
import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.viewModelScope
import core.common.BaseViewModel
import core.common.extentions.asText
import core.common.extentions.isNull

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import data.models.OtpUserCreate
import data.models.RegisterValidationData
import data.repositories.auth.AuthUserRepository
import domain.auth.OtpRegisterUseCase
import domain.auth.isIncorrectOtp
import core.common.validation.EmailValidationUseCase
import core.common.validation.PasswordValidationUseCase
import core.common.extentions.onError
import feature.register.impl.state.OtpState
import feature.register.impl.state.RegisterPayloadState
import feature.register.impl.state.RegisterUiState
import feature.register.impl.state.SideEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.example.eventify.uikit.R as UiKitR


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val otpRegisterUseCase: OtpRegisterUseCase,
    private val authRepository: AuthUserRepository,
    @ApplicationContext private val context: Context,
) : BaseViewModel() {

    private val emailValidationUseCase = EmailValidationUseCase()
    private val passwordValidationUseCase = PasswordValidationUseCase()

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

    fun updateOtp(value: String){
        if (value.length > 6) return

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

            val validLogin = emailValidationUseCase(login).onError { error ->
                mutableRegisterPayloadState.update { currentState ->
                    currentState.copy(
                        loginError = error.asText(context),
                        hasLoginError = true,
                    )
                }
            }

            val validPassword = passwordValidationUseCase(password).onError { error ->
                mutableRegisterPayloadState.update { currentState ->
                    currentState.copy(
                        passwordError = error.asText(context),
                        hasPasswordError = true,
                    )
                }
            }

            if (validLogin.isNull() || validPassword.isNull()) return@launchCatching

            val otpData = RegisterValidationData(
                email = validLogin!!,
                password = validPassword!!,
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

            // Show otp value is success
            mutableOtpState.update { currentState ->
                (currentState as? OtpState.ShowOtp)?.copy(
                    isSuccess = true,
                    hasError = false,
                    errorMessage = null,
                ) ?: currentState
            }
            delay(1000)
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
                        errorMessage = context.getString(UiKitR.string.incorrect_otp)
                    ) ?: currentState
                }
            }
            else -> {
                mutableSideEffect.trySend(SideEffect.FailedRegister)
                mutableOtpState.update { currentState ->
                    (currentState as? OtpState.ShowOtp)?.copy(
                        hasError = true,
                        errorMessage = context.getString(UiKitR.string.server_error)
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
package feature.register.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feature.register.api.RegisterListener
import feature.register.impl.components.RegistrationOtpBottomSheet
import feature.register.impl.state.OtpState
import feature.register.impl.state.RegisterPayloadState
import feature.register.impl.state.RegisterUiState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.components.ActionPrimaryText
import uikit.components.BodyText
import uikit.components.DisclaimerText
import uikit.components.ErrorInputText
import uikit.components.PasswordInput
import uikit.components.TextInput
import uikit.components.TitleText
import uikit.components.buttons.PrimaryButton
import com.example.eventify.uikit.R as UiKitR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterUiState,
    actions: RegisterListener,
) {
    val focusRequest = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val dimmentions = LocalDimentions.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true) {
        it != SheetValue.Hidden
    }


    if (state.otpState is OtpState.ShowOtp) {
        RegistrationOtpBottomSheet(
            onDismissRequest = {
                actions.onTriggerOtpBottomSheet(false)
            },
            sheetState = sheetState,
            onChangeOtpValue = actions::onChangeOtp,
            onSubmit = { otp ->
                actions.onRegister(
                    login = state.payloadState.login,
                    password = state.payloadState.password,
                    otp = otp,
                )
           },
            otpState = state.otpState,
        )
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimmentions.windowPaddings)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id=UiKitR.drawable.auth_icon), contentDescription = null)
            Spacer(modifier = Modifier.height(30.dp))
            TitleText(text = stringResource(UiKitR.string.register))
            Spacer(modifier = Modifier.height(10.dp))
            BodyText(text = stringResource(UiKitR.string.register_request))
            BodyText(text = stringResource(UiKitR.string.it_takes_less_then_minute))
            Spacer(modifier = Modifier.height(44.dp))
            TextInput(
                text = state.payloadState.login,
                label = "Email",
                placeholder = "ivanov@gmail.com",
                onValueChange = actions::onChangeLogin,
                isError = state.payloadState.hasLoginError,
                supportingText = {
                    state.payloadState.loginError?.let {
                        ErrorInputText(text = it)
                    }
                },
                modifier = Modifier.focusRequester(focusRequest),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordInput(
                text = state.payloadState.password,
                label = "Password",
                placeholder = "yourpassword",
                onValueChange = actions::onChangePassword,
                isError = state.payloadState.hasPasswordError,
                supportingText = {
                    state.payloadState.passwordError?.let {
                        ErrorInputText(text = it)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )

            Spacer(modifier = Modifier.height(30.dp))
            PrimaryButton(
                onClick = {
                    if (!state.isOtpRequested) {
                        actions.onRequestOtp(
                            login = state.payloadState.login,
                            password = state.payloadState.password,
                        )
                    }
              },
                enabled = state.payloadState.login.isNotEmpty() && state.payloadState.password.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(UiKitR.string.register_action),
                    lineHeight = 22.sp,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                Text(text = stringResource(UiKitR.string.already_have_account_question))
                ActionPrimaryText(
                    text = stringResource(UiKitR.string.login_action),
                    onClick = actions::navigateToLogIn
                )
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp)
        ) {
            DisclaimerText(
                text = stringResource(UiKitR.string.privacy_policy_text)
            )

            DisclaimerText(
                text = stringResource(UiKitR.string.privacy_policy_link),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { actions.goToPrivacyPolicy() }
            )
        }

    }


}


@Composable
@Preview(name = "Register Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Register Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun RegisterScreenErrorLightPreview(
    @PreviewParameter(RegisterPreviewParameterProvider::class) state: RegisterUiState
) {
    EventifyTheme {
        Surface {
            RegisterScreen(
                state = state,
                actions = object : RegisterListener {
                    override fun navigateToLogIn() = Unit
                    override fun onChangeLogin(login: String) = Unit
                    override fun onChangePassword(password: String) = Unit
                    override fun onRequestOtp(login: String, password: String) = Unit
                    override fun onRegister(login: String, password: String, otp: String) = Unit
                    override fun onChangeOtp(otpValue: String) = Unit
                    override fun onTriggerOtpBottomSheet(value: Boolean) = Unit
                    override fun goToPrivacyPolicy() = Unit
                }
            )
        }
    }
}


class RegisterPreviewParameterProvider : PreviewParameterProvider<RegisterUiState> {
    override val values = sequenceOf(
        RegisterUiState(),
        RegisterUiState(
            payloadState = RegisterPayloadState(
                login = "login@mail.ru",
                password = "asdfsadfsdf",
            )
        ),
        RegisterUiState(
            payloadState = RegisterPayloadState(
                login = "login@mail.ru",
                loginError = "Invalid email",
                hasLoginError = true,
                password = "asdfsadfsdf",
                passwordError = "Too short",
                hasPasswordError = true,
            )
        ),
    )
}




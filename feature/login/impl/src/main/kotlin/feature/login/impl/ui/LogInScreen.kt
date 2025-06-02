package feature.login.impl.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import feature.login.api.LoginListener
import feature.login.impl.state.LogInState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.TypographyKit
import uikit.components.PasswordInput
import uikit.components.TextInput
import uikit.components.buttons.PrimaryButtonWithLoader
import com.example.eventify.uikit.R as UiKitR

@Composable
internal fun LogInScreen(
    state: LogInState,
    actions: LoginListener,
) {
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val dimmentions = LocalDimentions.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimmentions.windowPaddings),
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id=UiKitR.drawable.login_icon), contentDescription = null)
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(UiKitR.string.log_in),
            style = TypographyKit.Heading.xlarge,
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(UiKitR.string.pleace_login),
            style = TypographyKit.Body.regular,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = stringResource(UiKitR.string.it_takes_less_then_minute),
            style = TypographyKit.Body.regular,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(44.dp))

        TextInput(
            text = state.login,
            label = "Email",
            placeholder = "ivanov@gmail.com",
            isError = state.loginError != null || state.hasLoginError,
            onValueChange = actions::onChangeLogin,
            supportingText = {
                state.loginError?.let { }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            modifier = Modifier
                .focusRequester(focusRequester)
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInput(
            text = state.password,
            label = "Password",
            placeholder = "mypassword",
            isError = state.passwordError != null || state.hasPasswordError,
            onValueChange = actions::onChangePassword,
            supportingText = {
                state.passwordError?.let {}
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
        Text(
            text = stringResource(UiKitR.string.forgot_password),
            textAlign = TextAlign.Right,
            style = TypographyKit.Body.regular,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    actions.navigateToResetPassword(sharedEmail = state.login)
                }
        )
        Spacer(modifier = Modifier.height(30.dp))
        PrimaryButtonWithLoader(
            text = stringResource(UiKitR.string.login_action),
            isEnabled = state.isValidForm,
            isLoading = false,
            onClick = {
                actions.login(
                    login = state.login,
                    password = state.password,
                )
            },
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(UiKitR.string.no_account_question),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(UiKitR.string.register_action),
                style = TypographyKit.Body.regular,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        actions.navigateToRegister()
                    }
            )
        }
    }
}

@Composable
@Preview(name = "LogIn Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "LogIn Light", showBackground = true,  uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun LogInScreenDarkPreview(@PreviewParameter(LoginPreviewParameterProvider::class) state: LogInState ) {
    EventifyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            LogInScreen(
                state = state,
                actions = object : LoginListener {
                    override fun onChangeLogin(login: String) = Unit
                    override fun onChangePassword(password: String) = Unit
                    override fun login(login: String, password: String) = Unit
                    override fun navigateToRegister() = Unit
                    override fun navigateToResetPassword(sharedEmail: String?) = Unit
                }
            )
        }
    }
}

class LoginPreviewParameterProvider : PreviewParameterProvider<LogInState> {
    override val values = sequenceOf(
        LogInState(),
        LogInState(
            login = "",
            loginError = "Короткий",
            password = "",
            passwordError = "Глупый"
        ),
    )
}
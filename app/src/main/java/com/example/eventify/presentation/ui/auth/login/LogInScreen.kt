package com.example.eventify.presentation.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.auth.login.state.LogInState
import com.example.eventify.presentation.ui.auth.login.state.LoginListener
import com.example.eventify.presentation.ui.common.ActionPrimaryText
import com.example.eventify.presentation.ui.common.ActionText
import com.example.eventify.presentation.ui.common.BodyText
import com.example.eventify.presentation.ui.common.ErrorInputText
import com.example.eventify.presentation.ui.common.PasswordInput
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.TextInput
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions

@Composable
fun LogInScreen(
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
        Image(painter = painterResource(id=R.drawable.login_icon), contentDescription = null)
        Spacer(modifier = Modifier.height(30.dp))
        TitleText(text = stringResource(R.string.log_in))
        Spacer(modifier = Modifier.height(10.dp))

        BodyText(text = stringResource(R.string.pleace_login))
        BodyText(text = stringResource(R.string.it_takes_less_then_minute))

        Spacer(modifier = Modifier.height(44.dp))

        TextInput(
            text = state.login,
            label = "Email",
            placeholder = "ivanov@gmail.com",
            isError = state.loginError != null || state.hasLoginError,
            onValueChange = actions::onChangeLogin,
            supportingText = {
                state.loginError?.let { ErrorInputText(text = it) }
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
                state.passwordError?.let { ErrorInputText(text = it) }
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
        ActionText(
            text = stringResource(R.string.forgot_password),
            onClick = {
                actions.navigateToResetPassword(sharedEmail = state.login)
            },
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        PrimaryButton(
            enabled = state.isValidForm,
            onClick = actions::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PrimaryButtonText(text = stringResource(R.string.login_action))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.no_account_question))
            ActionPrimaryText(
                text = stringResource(R.string.register_action),
                onClick = actions::navigateToRegister
            )
        }
    }
}

@Composable
@Preview(name = "LogIn Default Dark", showBackground = true)
private fun LogInScreenDarkPreview() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            LogInScreen(
                state = LogInState.default(),
                actions = object : LoginListener {
                    override fun onChangeLogin(login: String) = Unit
                    override fun onChangePassword(password: String) = Unit
                    override fun onSubmit() = Unit
                    override fun navigateToRegister() = Unit
                    override fun navigateToResetPassword(sharedEmail: String?) = Unit
                }
            )
        }
    }
}

@Composable
@Preview(name = "LogIn Invalid Form Dark", showBackground = true)
private fun LogInScreenInvalidDarkPreview() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            LogInScreen(
                state = LogInState(
                    login = "",
                    loginError = "Короткий",
                    password = "",
                    passwordError = "Глупый",
                ),
                actions = object : LoginListener {
                    override fun onChangeLogin(login: String) = Unit
                    override fun onChangePassword(password: String) = Unit
                    override fun onSubmit() = Unit
                    override fun navigateToRegister() = Unit
                    override fun navigateToResetPassword(sharedEmail: String?) = Unit
                }
            )
        }
    }
}

@Composable
@Preview(name = "LogIn Default Light", showBackground = true)
private fun LogInScreenLightPreview() {
    EventifyTheme(
        darkTheme = false
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            LogInScreen(
                state = LogInState.default(),
                actions = object : LoginListener {
                    override fun onChangeLogin(login: String) = Unit
                    override fun onChangePassword(password: String) = Unit
                    override fun onSubmit() = Unit
                    override fun navigateToRegister() = Unit
                    override fun navigateToResetPassword(sharedEmail: String?) = Unit
                }
            )
        }
    }
}

@Composable
@Preview(name = "LogIn Invalid Form Light", showBackground = true)
private fun LogInScreenInvalidLightPreview() {
    EventifyTheme(
        darkTheme = false
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            LogInScreen(
                state = LogInState(
                    login = "",
                    loginError = "Короткий",
                    password = "",
                    passwordError = "Глупый"
                ),
                actions = object : LoginListener {
                    override fun onChangeLogin(login: String) = Unit
                    override fun onChangePassword(password: String) = Unit
                    override fun onSubmit() = Unit
                    override fun navigateToRegister() = Unit
                    override fun navigateToResetPassword(sharedEmail: String?) = Unit
                }
            )
        }
    }
}


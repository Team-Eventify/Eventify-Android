package com.example.eventify.presentation.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.ui.shared.ActionPrimaryText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.ErrorInputText
import com.example.eventify.presentation.ui.shared.PasswordInput
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun RegisterScreen(
    state: RegisterState,
    actions: RegisterActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(text = "Регистрация")
        Spacer(modifier = Modifier.height(5.dp))
        BodyText(text = "Пожалуйста, создайте новый аккаунт.")
        BodyText(text = "Это займёт меньше минуты.")
        Spacer(modifier = Modifier.height(30.dp))
        TextInput(
            text = state.login,
            label = "Email",
            placeholder = "ivanov@gmail.com",
            onValueChange = actions.onChangeLogin,
            isError = state.loginError != null || state.hasLoginError,
            supportingText = {
                state.loginError?.let { 
                    ErrorInputText(text = it)
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInput(
            text = state.password,
            label = "Password",
            placeholder = "yourpassword",
            onValueChange = actions.onChangePassword,
            isError = state.passwordError != null || state.hasPasswordError,
            supportingText = {
                state.passwordError?.let {
                    ErrorInputText(text = it)
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        PrimaryButton(
            onClick = actions.onSubmit,
            enabled = state.isValidFormData,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Зарегестрироваться", lineHeight = 22.sp, fontSize = 17.sp, fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Text(text = "Уже есть аккаунт?")
            ActionPrimaryText(
                text = "Войти",
                onClick = actions.navigateToLogIn
            )
        }
    }
}

@Composable
@Preview(name = "Register Default Dark", showBackground = true)
private fun RegisterScreenDefaultDarkPreview() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            RegisterScreen(
                state = RegisterState.default(),
                actions = RegisterActions(
                    onChangeLogin = {},
                    onChangePassword = {},
                    onSubmit = {},
                    navigateToLogIn = {}
                )
            )
        }
    }
}

@Composable
@Preview(name = "Register Error Dark", showBackground = true)
private fun RegisterScreenErrorDarkPreview() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            RegisterScreen(
                state = RegisterState(
                    login = "",
                    hasLoginError = true,
                    password = "",
                    passwordError = "Слишком простой пароль"
                ),
                actions = RegisterActions(
                    onChangeLogin = {},
                    onChangePassword = {},
                    onSubmit = {},
                    navigateToLogIn = {}
                )
            )
        }
    }
}

@Composable
@Preview(name = "Register Default Light", showBackground = true)
private fun RegisterScreenDefaultLightPreview() {
    EventifyTheme(
        darkTheme = false
    ) {
        Surface {
            RegisterScreen(
                state = RegisterState.default(),
                actions = RegisterActions(
                    onChangeLogin = {},
                    onChangePassword = {},
                    onSubmit = {},
                    navigateToLogIn = {}
                )
            )
        }
    }
}

@Composable
@Preview(name = "Register Error Light", showBackground = true)
private fun RegisterScreenErrorLightPreview() {
    EventifyTheme(
        darkTheme = false
    ) {
        Surface {
            RegisterScreen(
                state = RegisterState(
                    login = "",
                    hasLoginError = true,
                    password = "",
                    passwordError = "Слишком простой пароль"
                ),
                actions = RegisterActions(
                    onChangeLogin = {},
                    onChangePassword = {},
                    onSubmit = {},
                    navigateToLogIn = {}
                )
            )
        }
    }
}


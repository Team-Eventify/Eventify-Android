package feature.resetPassword.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import feature.resetPassword.api.ResetPasswordListener
import feature.resetPassword.impl.state.UiState
import uikit.EventifyTheme
import uikit.LocalDimentions

@Composable
fun ResetPasswordScreen(
    state: UiState,
    actions: ResetPasswordListener,
) {
    val dimmentions = LocalDimentions.current

    Column(modifier = Modifier
            .fillMaxSize()
            .padding(dimmentions.windowPaddings),
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id= R.drawable.resetpassword_icon), contentDescription = null)
        Spacer(modifier = Modifier.height(30.dp))
        TitleText(text = "Сброс пароля")
        Spacer(modifier = Modifier.height(12.dp))
        BodyText(
            text = "Укажите email, который вы использовали для создания аккаунта. Мы отправим письмо с сыллкой для сброса пароля."
        )
        Spacer(modifier = Modifier.height(44.dp))

        TextInput(
            text = state.email,
            onValueChange = actions::changeEmail,
            label = "Email"
        )
        Spacer(modifier = Modifier.height(22.dp))

        PrimaryButton(
            onClick = actions::submit,
            enabled = state.isValidEmail
        ) {
            PrimaryButtonText(text = "Отправить")
        }
    }
}

@Composable
@Preview(name = "ResetPassword", showBackground = true)
private fun ResetPasswordScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ResetPasswordScreen(
                state = UiState(),
                actions = object : ResetPasswordListener {
                    override fun submit() = Unit
                    override fun changeEmail(email: String) = Unit
                }
            )
        }
    }
}


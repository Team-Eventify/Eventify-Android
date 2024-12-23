package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ResetPasswordScreen(
    state: ResetPasswordState,
    actions: ResetPasswordActions,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        TitleText(text = "Сброс пароля")
        BodyText(
            text = "Укажите email, который вы использовали для создания аккаунта. Мы отправим письмо с сыллкой для сброса пароля."
        )
        TextInput(
            text = state.email,
            onValueChange = actions.onChangeEmail,
            label = "Email"
        )
        PrimaryButton(
            onClick = actions.onSubmit,
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
                state = ResetPasswordState(),
                actions = ResetPasswordActions()
            )
        }
    }
}


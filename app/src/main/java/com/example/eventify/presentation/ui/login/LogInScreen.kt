package com.example.eventify.presentation.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.PasswordInput
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.ui.shared.TitleText

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(text = "Вход")
        Spacer(modifier = modifier.height(5.dp))

        BodyText(text = "Пожалуйста,  войдите в свой аккаунт.")
        BodyText(text = "Пожалуйста,  войдите в свой аккаунт.")

        Spacer(modifier = modifier.height(30.dp))

        TextInput(
            placeholder = "Email"
        )
        Spacer(modifier = modifier.height(10.dp))
        PasswordInput(
            placeholder = "Password"
        )
        Text(
            text = "Забыли пароль?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 17.sp,
            textAlign = TextAlign.Right,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(30.dp))
        PrimaryButton(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
        ) {
            PrimaryButtonText(text = "Войти")
        }
        Spacer(modifier = modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Text(text = "Нет аккаунта?")
            Text(text = "Регистрация", textDecoration = TextDecoration.Underline)
        }

    }
}

@Preview(name = "LogInScreen", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLogInScreen() {
    LogInScreen()
}
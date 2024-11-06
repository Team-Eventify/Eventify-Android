package com.example.eventify.presentation.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.presentation.ui.navgraphs.AuthRouter
import com.example.eventify.presentation.ui.navgraphs.RootRouter
import com.example.eventify.presentation.ui.shared.ActionPrimaryText
import com.example.eventify.presentation.ui.shared.ActionText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.PasswordInput
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.viewmodels.LogInViewModel

@Composable
fun LogInScreen(
    navController: NavHostController,
    logInViewModel: LogInViewModel = hiltViewModel(),
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
        BodyText(text = "Это займёт меньше минуты.")

        Spacer(modifier = modifier.height(30.dp))

        TextInput(
            text = logInViewModel.loginValue,
            placeholder = "Email",
            onValueChange = {
                logInViewModel.loginValue = it
            }
        )
        Spacer(modifier = modifier.height(10.dp))
        PasswordInput(
            text = logInViewModel.passwordValue,
            placeholder = "Password",
            onValueChange = {
                logInViewModel.passwordValue = it
            }
        )
        ActionText(
            text = "Забыли пароль?",
            onClick = {},
            textAlign = TextAlign.Right,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(30.dp))
        PrimaryButton(
            onClick = {
                logInViewModel.logIn(onSuccess = {
                    navController.navigate(RootRouter.HomeRoute.route)
                })
            },
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
            ActionPrimaryText(
                text = "Зарегестрироваться",
                onClick = {
                    navController.navigate(AuthRouter.RegisterRoute.route)
                }
            )
        }

    }
}

@Preview(name = "LogInScreen", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLogInScreen() {
    LogInScreen(navController = rememberNavController())
}
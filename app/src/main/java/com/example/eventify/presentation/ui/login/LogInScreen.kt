package com.example.eventify.presentation.ui.login

import android.content.res.Configuration
import android.os.Message
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.presentation.models.LogInUiState
import com.example.eventify.presentation.models.LoginResult
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
import kotlinx.coroutines.launch

@Composable
fun LogInScreenComponent(
    navController: NavHostController,
    logInUiState: LogInUiState,
    loginResult: LoginResult,
    onLoginValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onLogIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(loginResult) {
        when (loginResult){
            is LoginResult.Success -> {
                navController.navigate(RootRouter.HomeRoute.route)
            }
            is LoginResult.Error -> {
                snackbarHostState.showSnackbar(loginResult.message)
            }
            else -> null
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ){ _ ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TitleText(text = "Вход")
            Spacer(modifier = modifier.height(5.dp))

            BodyText(text = "Пожалуйста,  войдите в свой аккаунт.")
            BodyText(text = "Это займёт меньше минуты.")

            Spacer(modifier = modifier.height(30.dp))

            TextInput(
                text = logInUiState.loginValue,
                label = "Email",
                placeholder = "ivanov@gmail.com",
                isError = logInUiState.hasErrors,
                onValueChange = onLoginValueChange
            )
            Spacer(modifier = modifier.height(10.dp))
            PasswordInput(
                text = logInUiState.passwordValue,
                label = "Password",
                placeholder = "mypassword",
                isError = logInUiState.hasErrors,
                onValueChange = onPasswordValueChange
            )
            ActionText(
                text = "Забыли пароль?",
                onClick = {},
                textAlign = TextAlign.Right,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(30.dp))
            PrimaryButton(
                enabled = logInUiState.isValidData,
                onClick = {
                  onLogIn()
                },
                modifier = modifier
                    .fillMaxWidth()
            ) {
                PrimaryButtonText(text = "Войти")
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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

}


@Composable
fun LogInScreen(
    navController: NavHostController,
    viewModel: LogInViewModel = hiltViewModel()
) {
    LogInScreenComponent(
        navController = navController,
        logInUiState = viewModel.uiState,
        onLoginValueChange = viewModel::changeLoginValue,
        onPasswordValueChange = viewModel::changePasswordValue,
        onLogIn = viewModel::logIn,
        loginResult = viewModel.loginResult
    )
}

@Preview(name = "LogInScreen", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLogInScreen() {
    LogInScreenComponent(
        navController = rememberNavController(),
        loginResult = LoginResult.Idle,
        logInUiState = LogInUiState.default(),
        onLoginValueChange = {},
        onPasswordValueChange = {},
        onLogIn = {  },
    )
}
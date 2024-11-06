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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.presentation.ui.navgraphs.AuthRouter
import com.example.eventify.presentation.ui.navgraphs.RootRouter
import com.example.eventify.presentation.ui.shared.ActionPrimaryText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.PasswordInput
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.ui.shared.TitleText

@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        TitleText(text = "Регистрация")
        Spacer(modifier = modifier.height(5.dp))
        BodyText(text = "Пожалуйста, создайте новый аккаунт.")
        BodyText(text = "Это займёт меньше минуты.")
        Spacer(modifier = modifier.height(30.dp))
        TextInput(
            text = email,
            placeholder = "Email",
            onValueChange = {
                email = it
            }
        )
        Spacer(modifier = modifier.height(10.dp))
        PasswordInput(
            text = password,
            placeholder = "Password",
            onValueChange = {
                password = it
            }
        )

        Spacer(modifier = modifier.height(30.dp))
        PrimaryButton(
            onClick = {
              navController.navigate(RootRouter.HomeRoute.route)
            },
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(text = "Зарегестрироваться", lineHeight = 22.sp, fontSize = 17.sp, fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            Text(text = "Уже есть аккаунт?")
            ActionPrimaryText(
                text = "Войти",
                onClick = {
                    navController.navigate(AuthRouter.LogInRoute.route)
                }
            )
        }

    }
}

@Preview(name = "RegisterScreen", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}
package com.example.eventify.presentation.ui.navgraphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.login.LogInScreen
import com.example.eventify.presentation.ui.login.RegisterScreen
import com.example.eventify.presentation.viewmodels.LogInViewModel
import kotlinx.serialization.Serializable

fun NavGraphBuilder.AuthNavigationGraph(navController: NavHostController) {
    navigation<RootRouter.Auth>(
        startDestination = AuthRouter.LogIn
    ){
        composable<AuthRouter.LogIn> {
            LogInScreen(navController = navController)
        }

        composable<AuthRouter.Register> {
            RegisterScreen(navController = navController)
        }

    }
}


sealed class AuthRouter{
    @Serializable
    data object LogIn : AuthRouter()
    @Serializable
    data object Register : AuthRouter()
}
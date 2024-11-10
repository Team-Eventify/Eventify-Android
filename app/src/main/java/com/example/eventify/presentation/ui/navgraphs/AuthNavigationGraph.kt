package com.example.eventify.presentation.ui.navgraphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
        composable<AuthRouter.LogIn>(
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { it }, // Уезжает вверх
                    animationSpec = tween(durationMillis = 700) // Настройка длительности
                )
            },

        ){
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
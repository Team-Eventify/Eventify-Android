package com.example.eventify.presentation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.login.LogInRoute
import com.example.eventify.presentation.ui.templogin.RegisterScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.AuthNavGraph(
    navController: NavHostController,
    startDestination: AuthRouter = AuthRouter.LogInRoute
) {
    navigation<RootRouter.AuthRoute>(
        startDestination = startDestination
    ){
        composable<AuthRouter.LogInRoute> {
            LogInRoute(navController = navController)
        }
        composable<AuthRouter.RegisterRoute> {
            RegisterScreen(navController = navController)
        }
    }
}

sealed class AuthRouter{
    @Serializable
    data object LogInRoute : AuthRouter()

    @Serializable
    data object RegisterRoute : AuthRouter()
}
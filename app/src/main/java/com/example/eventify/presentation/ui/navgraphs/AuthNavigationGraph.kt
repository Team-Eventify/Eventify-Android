package com.example.eventify.presentation.ui.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.login.LogInScreen
import com.example.eventify.presentation.ui.login.RegisterScreen

fun NavGraphBuilder.AuthNavigationGraph(navController: NavHostController) {
    navigation(
        route = RootRouter.AuthRoute.route,
        startDestination = AuthRouter.LogInRoute.route
    ){
        composable(AuthRouter.LogInRoute.route) {
            LogInScreen(navController = navController)
        }

        composable(AuthRouter.RegisterRoute.route) {
            RegisterScreen(navController = navController)
        }

    }
}


sealed class AuthRouter(val route: String){
    data object LogInRoute : AuthRouter(route = "login")
    data object RegisterRoute : AuthRouter(route = "register")
}
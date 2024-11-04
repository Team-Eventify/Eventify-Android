package com.example.eventify.presentation.ui.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.MainScreen
import com.example.eventify.presentation.ui.login.LogInScreen
import com.example.eventify.presentation.ui.login.RegisterScreen


@Composable
fun RootNavGraph(
    startDestination: RootRouter = RootRouter.AuthRoute,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ){
        AuthNavigationGraph(navController)

        composable(RootRouter.HomeRoute.route) {
            MainScreen()
        }
    }
}

sealed class RootRouter(val route: String) {
    object AuthRoute: RootRouter(route = "auth")
    object HomeRoute: RootRouter(route = "home")
}


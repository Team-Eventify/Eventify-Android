package com.example.eventify.presentation.ui.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.MainScreen
import com.example.eventify.presentation.ui.login.LogInScreen
import com.example.eventify.presentation.ui.login.RegisterScreen
import kotlinx.serialization.Serializable


@Composable
fun RootNavGraph(
    startDestination: RootRouter = RootRouter.Auth,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        AuthNavigationGraph(navController)

        composable<RootRouter.Home> {
            MainScreen(rootNavController = navController)
        }
    }
}

sealed class RootRouter {
    @Serializable
    data object Auth: RootRouter()
    @Serializable
    data object Home: RootRouter()
}


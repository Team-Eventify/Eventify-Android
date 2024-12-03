package com.example.eventify.presentation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.serialization.Serializable


@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: RootRouter = RootRouter.AuthRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        HomeNavGraph(navController = navController)
        AuthNavGraph(navController = navController)
    }
}

sealed class RootRouter{
    @Serializable
    data object HomeRoute : RootRouter()

    @Serializable
    data object AuthRoute : RootRouter()
}
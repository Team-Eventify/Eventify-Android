package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.eventdetail.EventDetailRoute
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
        AuthNavGraph()
        SettingsNavGraph()
        
        composable<RootRouter.EventDetailRoute>{
            EventDetailRoute(navController = navController)
        }
    }
}

sealed class RootRouter: Destination {
    @Serializable
    data object HomeRoute : RootRouter()

    @Serializable
    data object AuthRoute : RootRouter()
    
    @Serializable
    data class EventDetailRoute(val eventId: String) : RootRouter()

    @Serializable
    data object SettingsRoute : RootRouter()
}
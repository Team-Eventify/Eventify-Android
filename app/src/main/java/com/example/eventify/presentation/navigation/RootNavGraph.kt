package com.example.eventify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.MainScreen
import com.example.eventify.presentation.ui.events.EventDetailScreen
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
        
        composable<RootRouter.EventDetail> { 
            EventDetailScreen(navController = navController)
        }
    }
}

sealed class RootRouter {
    @Serializable
    data object Auth: RootRouter()
    @Serializable
    data object Home: RootRouter()

    @Serializable
    data object Events: RootRouter()

    @Serializable
    data class EventDetail(val eventId: String) : RootRouter()
}


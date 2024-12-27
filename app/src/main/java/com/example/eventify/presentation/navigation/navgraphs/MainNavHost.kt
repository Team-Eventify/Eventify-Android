package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailRoute
import kotlinx.serialization.Serializable


@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: RootRouter = RootRouter.AuthRoute,
    scaffoldViewState: MutableState<ScaffoldViewState>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.then(modifier)
    ){
        HomeNavGraph(navController = navController, scaffoldViewState = scaffoldViewState)
        AuthNavGraph(scaffoldViewState = scaffoldViewState)
        SettingsNavGraph(scaffoldViewState = scaffoldViewState)
        
        composable<RootRouter.EventDetailRoute>{
            EventDetailRoute(
                navController = navController,
                scaffoldViewState = scaffoldViewState
            )
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
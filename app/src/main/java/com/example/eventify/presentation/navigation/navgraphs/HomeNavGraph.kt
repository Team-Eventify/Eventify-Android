package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.eventsfeed.EventsFeedRoute
import com.example.eventify.presentation.ui.myevents.MyEventsRoute
import com.example.eventify.presentation.ui.profile.ProfileRoute
import com.example.eventify.presentation.ui.shared.NotImplementedScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.HomeNavGraph(
    navController: NavHostController,
    startDestination: HomeRouter = HomeRouter.EventFeed
) {
    navigation<RootRouter.HomeRoute>(
        startDestination = startDestination
    ){
        composable<HomeRouter.EventFeed> {
            EventsFeedRoute(navController = navController)
        }
        composable<HomeRouter.Profile> {
            ProfileRoute()
        }
        composable<HomeRouter.Search> {
            NotImplementedScreen()
        }
        composable<HomeRouter.SelfEvents> {
            MyEventsRoute()
        }
    }
}


sealed class HomeRouter: Destination {
    @Serializable
    data object EventFeed : HomeRouter()
    @Serializable
    data object SelfEvents : HomeRouter()
    @Serializable
    data object Profile : HomeRouter()
    @Serializable
    data object Search : HomeRouter()
}
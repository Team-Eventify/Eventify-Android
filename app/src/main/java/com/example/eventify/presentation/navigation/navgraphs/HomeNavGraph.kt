package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.account.profile.ProfileRoute
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedRoute
import com.example.eventify.presentation.ui.events.myevents.MyEventsRoute
import com.example.eventify.presentation.ui.events.search.SearchRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.HomeNavGraph(
    navController: NavHostController,
    scaffoldViewState: MutableState<ScaffoldViewState>,
    startDestination: HomeRouter = HomeRouter.EventFeed
) {
    navigation<RootRouter.HomeRoute>(
        startDestination = startDestination
    ){
        composable<HomeRouter.EventFeed> {
            EventsFeedRoute(navController = navController, scaffoldViewState = scaffoldViewState)
        }
        composable<HomeRouter.Profile> {
            ProfileRoute(scaffoldViewState = scaffoldViewState)
        }
        composable<HomeRouter.Search> {
            SearchRoute()
        }
        composable<HomeRouter.SelfEvents> {
            MyEventsRoute(
                scaffoldViewState = scaffoldViewState
            )
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
    data class Search(val query: String? = null) : HomeRouter()
}
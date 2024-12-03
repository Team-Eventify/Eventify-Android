package com.example.eventify.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.events.EventDetailScreen
import com.example.eventify.presentation.ui.events.EventsFeedScreen
import com.example.eventify.presentation.ui.events.SearchScreen
import com.example.eventify.presentation.ui.eventsfeed.EventsFeedRoute
import com.example.eventify.presentation.ui.eventsfeed.rememberEventsFeedCoordinator
import com.example.eventify.presentation.ui.myevents.MyEventsScreen
import com.example.eventify.presentation.ui.shared.NotImplementedScreen
import com.example.eventify.presentation.viewmodels.EventsViewModel
import com.example.eventify.presentation.viewmodels.UserViewModel
import kotlinx.serialization.Serializable

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    rootNavController: NavHostController
) {
    val eventsViewModel = hiltViewModel<EventsViewModel>()
    val userViewModel = hiltViewModel<UserViewModel>()
    val eventsFeedCoordinator = rememberEventsFeedCoordinator(navController)


    NavHost(
        navController = navController,
        startDestination = HomeRouter.EventFeed,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ){
        profileNavigationGraph(navController, userViewModel, rootNavController = rootNavController)
        composable<HomeRouter.EventFeed> {
            EventsFeedRoute(navController = navController, coordinator = eventsFeedCoordinator)
        }
        composable<HomeRouter.SelfEvents> {
            MyEventsScreen()
        }
        composable<HomeRouter.Search> {
            NotImplementedScreen()
        }
    }
}


sealed class HomeRouter{
    @Serializable
    data object EventFeed : HomeRouter()
    @Serializable
    data object SelfEvents : HomeRouter()
    @Serializable
    data object Profile : HomeRouter()
    @Serializable
    data object Search : HomeRouter()
    @Serializable
    data class EventDetail(val eventId: String) : HomeRouter()
}


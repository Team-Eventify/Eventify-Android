package com.example.eventify.presentation.ui.navgraphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.events.EventDetailScreen
import com.example.eventify.presentation.ui.events.EventsFeedScreen
import com.example.eventify.presentation.ui.events.SearchScreen
import com.example.eventify.presentation.ui.profile.ProfileEditScreen
import kotlinx.serialization.Serializable

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRouter.EventFeed,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ){
        profileNavigationGraph(navController)
        composable<HomeRouter.EventFeed> {
            EventsFeedScreen(navController = navController)
        }
        composable<HomeRouter.Favorites> {
            EventDetailScreen()
        }
        composable<HomeRouter.Search> {
            SearchScreen()
        }
        composable<HomeRouter.EventDetail> {
            EventDetailScreen()
        }
    }
}


sealed class HomeRouter{
    @Serializable
    data object EventFeed : HomeRouter()
    @Serializable
    data object Favorites : HomeRouter()
    @Serializable
    data object Profile : HomeRouter()
    @Serializable
    data object Search : HomeRouter()
    @Serializable
    data class EventDetail(val eventId: String) : HomeRouter()
}


package com.example.eventify.presentation.ui.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.events.EventDetailScreen
import com.example.eventify.presentation.ui.events.EventsFeedScreen
import com.example.eventify.presentation.ui.profile.ProfileEditScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = RootRouter.HomeRoute.route,
        startDestination = HomeRouter.EventFeedRoute.route
    ){
        composable(HomeRouter.EventFeedRoute.route) {
            EventsFeedScreen()
        }
        composable(HomeRouter.FavoritesRoute.route) {
            EventDetailScreen()
        }
        composable(HomeRouter.ProfileRoute.route) {
            ProfileEditScreen()
        }
    }
}


sealed class HomeRouter(val route: String){
    object EventFeedRoute : HomeRouter(route = "feed")
    object FavoritesRoute : HomeRouter(route = "favorites")
    object ProfileRoute : HomeRouter(route = "profile")
}


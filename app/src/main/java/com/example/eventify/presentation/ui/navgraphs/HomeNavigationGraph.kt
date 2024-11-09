package com.example.eventify.presentation.ui.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.events.EventDetailScreen
import com.example.eventify.presentation.ui.events.EventsFeedScreen
import com.example.eventify.presentation.ui.events.SearchScreen
import com.example.eventify.presentation.ui.profile.ProfileEditScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = RootRouter.HomeRoute.route,
        startDestination = HomeRouter.EventFeedRoute.route
    ){
        profileNavigationGraph(navController)
        composable(HomeRouter.EventFeedRoute.route) {
            EventsFeedScreen(navController = navController)
        }
        composable(HomeRouter.FavoritesRoute.route) {
            EventDetailScreen()
        }
        composable(HomeRouter.SearchRoute.route) {
            SearchScreen()
        }
    }
}


sealed class HomeRouter(val route: String){
    data object EventFeedRoute : HomeRouter(route = "feed")
    data object FavoritesRoute : HomeRouter(route = "favorites")
    data object ProfileRoute : HomeRouter(route = "profile")
    data object SearchRoute : HomeRouter(route = "search")
}


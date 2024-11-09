package com.example.eventify.presentation.ui.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.profile.ProfileEditScreen
import com.example.eventify.presentation.ui.profile.ProfileScreen


fun NavGraphBuilder.profileNavigationGraph(navController: NavHostController){
    navigation(
        startDestination = ProfileRouter.DetailProfile.route,
        route = HomeRouter.ProfileRoute.route
    ){
        composable(ProfileRouter.DetailProfile.route) {
            ProfileScreen(navController)
        }
        composable(ProfileRouter.EditProfile.route) {
            ProfileEditScreen()
        }
    }
}


sealed class ProfileRouter(val route: String){
    data object DetailProfile : ProfileRouter(route = "profile_detail")
    data object EditProfile : ProfileRouter(route = "profile_edit")
}
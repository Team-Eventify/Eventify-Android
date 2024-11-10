package com.example.eventify.presentation.ui.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.profile.ProfileEditScreen
import com.example.eventify.presentation.ui.profile.ProfileScreen
import kotlinx.serialization.Serializable


fun NavGraphBuilder.profileNavigationGraph(navController: NavHostController){
    navigation<HomeRouter.Profile>(
        startDestination = ProfileRouter.DetailProfile,
    ){
        composable<ProfileRouter.DetailProfile> {
            ProfileScreen(navController)
        }
        composable<ProfileRouter.EditProfile> {
            ProfileEditScreen(navController)
        }
    }
}


sealed class ProfileRouter{
    @Serializable
    data object DetailProfile : ProfileRouter()
    @Serializable
    data object EditProfile : ProfileRouter()
}
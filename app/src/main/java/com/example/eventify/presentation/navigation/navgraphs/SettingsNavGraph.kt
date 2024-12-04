package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.profile.ProfileEditScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.SettingsNavGraph(
    navController: NavHostController,
    startDestination: SettingsRouter = SettingsRouter.ProfileEditRoute
) {
    navigation<RootRouter.SettingsRoute>(
        startDestination = startDestination
    ){
        composable<SettingsRouter.ProfileEditRoute>{
            ProfileEditScreen(navController = navController)
        }
    }
}


sealed class SettingsRouter: Destination {
    @Serializable
    data object ProfileEditRoute : SettingsRouter()
}
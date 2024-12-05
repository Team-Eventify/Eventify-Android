package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

fun NavGraphBuilder.SettingsNavGraph(
    startDestination: SettingsRouter = SettingsRouter.ProfileEditRoute
) {
    navigation<RootRouter.SettingsRoute>(
        startDestination = startDestination
    ){
        composable<SettingsRouter.ProfileEditRoute>{
        }
    }
}


sealed class SettingsRouter: Destination {
    @Serializable
    data object ProfileEditRoute : SettingsRouter()
}
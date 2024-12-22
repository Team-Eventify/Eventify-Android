package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.profileedit.ProfileEditRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.SettingsNavGraph(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    startDestination: SettingsRouter = SettingsRouter.ProfileEditRoute
) {
    navigation<RootRouter.SettingsRoute>(
        startDestination = startDestination
    ){
        composable<SettingsRouter.ProfileEditRoute>{
            ProfileEditRoute(scaffoldViewState = scaffoldViewState)
        }
    }
}


sealed class SettingsRouter: Destination {
    @Serializable
    data object ProfileEditRoute : SettingsRouter()
}
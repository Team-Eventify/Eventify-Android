package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.account.aboutapp.AboutAppRoute
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditRoute
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
        composable<SettingsRouter.AboutAppRoute>{
            AboutAppRoute(scaffoldViewState = scaffoldViewState)
        }
    }
}


sealed class SettingsRouter: Destination {
    @Serializable
    data object ProfileEditRoute : SettingsRouter()

    @Serializable
    data object AboutAppRoute : SettingsRouter()
}
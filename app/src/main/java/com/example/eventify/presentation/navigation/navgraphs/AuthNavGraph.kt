package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.login.LogInRoute
import com.example.eventify.presentation.ui.register.RegisterRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.AuthNavGraph(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    startDestination: AuthRouter = AuthRouter.LogInRoute
) {
    navigation<RootRouter.AuthRoute>(
        startDestination = startDestination
    ){
        composable<AuthRouter.LogInRoute> {
            LogInRoute(scaffoldViewState = scaffoldViewState)
        }
        composable<AuthRouter.RegisterRoute> {
            RegisterRoute(scaffoldViewState = scaffoldViewState)
        }
    }
}


sealed class AuthRouter: Destination {
    @Serializable
    data object LogInRoute : AuthRouter()

    @Serializable
    data object RegisterRoute : AuthRouter()
}

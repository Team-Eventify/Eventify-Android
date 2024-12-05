package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.login.LogInRoute
import com.example.eventify.presentation.ui.register.RegisterRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.AuthNavGraph(
    startDestination: AuthRouter = AuthRouter.LogInRoute
) {
    navigation<RootRouter.AuthRoute>(
        startDestination = startDestination
    ){
        composable<AuthRouter.LogInRoute> {
            LogInRoute()
        }
        composable<AuthRouter.RegisterRoute> {
            RegisterRoute()
        }
    }
}


sealed class AuthRouter: Destination {
    @Serializable
    data object LogInRoute : AuthRouter()

    @Serializable
    data object RegisterRoute : AuthRouter()
}

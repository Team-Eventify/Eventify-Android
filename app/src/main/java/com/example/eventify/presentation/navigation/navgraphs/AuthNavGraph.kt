package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.auth.choosecategories.ChooseCategoriesRoute
import com.example.eventify.presentation.ui.auth.login.LogInRoute
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingRoute
import com.example.eventify.presentation.ui.auth.register.RegisterRoute
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.AuthNavGraph(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    startDestination: AuthRouter = AuthRouter.RegisterRoute
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
        composable<AuthRouter.ChooseCategoriesRoute> {
            ChooseCategoriesRoute()
        }
        composable<AuthRouter.ResetPasswordRoute> {
            ResetPasswordRoute()
        }
    }
}


sealed class AuthRouter: Destination {
    @Serializable
    data object LogInRoute : AuthRouter()

    @Serializable
    data object RegisterRoute : AuthRouter()

    @Serializable
    data class ResetPasswordRoute(val email: String? = null) : AuthRouter()

    @Serializable
    data object ChooseCategoriesRoute: AuthRouter()
}

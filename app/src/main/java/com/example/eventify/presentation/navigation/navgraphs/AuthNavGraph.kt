package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.auth.choosecategories.ChooseCategoriesRoute
import com.example.eventify.presentation.ui.auth.login.LogInRoute
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingRoute
import com.example.eventify.presentation.ui.auth.privacypolicy.PrivacyPolicyRoute
import com.example.eventify.presentation.ui.auth.register.RegisterRoute
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.AuthNavGraph(
    startDestination: AuthRouter = AuthRouter.RegisterRoute
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
        composable<AuthRouter.ChooseCategoriesRoute> {
            ChooseCategoriesRoute()
        }
        composable<AuthRouter.ResetPasswordRoute> {
            ResetPasswordRoute()
        }
        composable<AuthRouter.PrivacyPolicyRoute> {
            PrivacyPolicyRoute()
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

    @Serializable
    data object PrivacyPolicyRoute: AuthRouter()
}

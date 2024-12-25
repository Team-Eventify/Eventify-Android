package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.auth.onboarding.OnboardingEndScreen
import com.example.eventify.presentation.ui.auth.onboarding.OnboardingStartScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.OnBoardingGraph(){
    navigation<OnBoardingRouter>(
        startDestination = OnBoardingRouter.StartRoute
    ){
        composable<OnBoardingRouter.StartRoute> {
            OnboardingStartScreen()
        }

        composable<OnBoardingRouter.EndRoute> {
            OnboardingEndScreen()
        }
    }
}

sealed interface OnBoardingRouter{
    @Serializable
    data object StartRoute : OnBoardingRouter

    @Serializable
    data object EndRoute : OnBoardingRouter
}
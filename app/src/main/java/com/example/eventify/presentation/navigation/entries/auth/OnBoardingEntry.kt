package com.example.eventify.presentation.navigation.entries.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.register.RegisterRoute
import javax.inject.Inject

val OnBoardingPath = AuthRootPath.updateAndGetPath("onboarding")

interface OnBoardingEntry : ComposableFeatureEntry {
    override val route: String
        get() = OnBoardingPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class OnBoardingEntryImpl @Inject constructor() : OnBoardingEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        RegisterRoute(navController)
    }
}
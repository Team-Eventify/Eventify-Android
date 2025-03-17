package com.example.eventify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface FeatureEntry {
    val destination: BaseDestination
}

interface ComposableFeatureEntry : FeatureEntry {

    fun NavGraphBuilder.featureComposable(
        navController: NavHostController,
        deepLinks: List<NavDeepLink> = emptyList()
    ) {
        composable(
            this@ComposableFeatureEntry.destination.baseRoute,
            deepLinks = deepLinks
        ) {
            Composable(navController)
        }
    }

    @Composable
    fun Composable(navController: NavHostController)
}
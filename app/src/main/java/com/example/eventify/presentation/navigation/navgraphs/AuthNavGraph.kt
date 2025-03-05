package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import com.example.eventify.presentation.ui.auth.login.LoginPath


fun NavGraphBuilder.addAuthNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
    ) {
    navigation(
        route = AuthRootPath.baseRoute,
        startDestination = LoginPath
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}

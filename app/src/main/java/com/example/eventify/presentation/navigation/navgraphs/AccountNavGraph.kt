package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.profile.AccountRootPath
import com.example.eventify.presentation.ui.account.profile.ProfilePath


fun NavGraphBuilder.addAccountNavGraph(
    navController: NavHostController,
    features: List<ComposableFeatureEntry>,
) {
    navigation(
        route = AccountRootPath.baseRoute,
        startDestination = ProfilePath
    ) {
        features.forEach { feature ->
            with(feature) {
                featureComposable(navController)
            }
        }
    }
}

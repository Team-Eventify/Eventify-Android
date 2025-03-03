package com.example.eventify.presentation.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.navigation.entries.auth.AuthRootPath
import com.example.eventify.presentation.navigation.entries.auth.LoginPath


import kotlinx.serialization.Serializable

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

package core.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface FeatureEntry {
    val destination: BaseDestination
}

interface ComposableFeatureEntry : FeatureEntry {

    val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?
        get() = null

    val exitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?
        get() = null

    val popEnterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?
        get() = enterTransition

    val popExitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?
        get() = exitTransition

    val sizeTransform: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)?
        get() = null

    fun NavGraphBuilder.featureComposable(
        navController: NavHostController,
        deepLinks: List<NavDeepLink> = emptyList()
    ) {
        composable(
            this@ComposableFeatureEntry.destination.baseRoute,
            deepLinks = deepLinks,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popExitTransition = popExitTransition,
            popEnterTransition = popEnterTransition,
            sizeTransform = sizeTransform,
        ) {
            Composable(navController)
        }
    }

    @Composable
    fun Composable(navController: NavHostController)
}
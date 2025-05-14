package feature.profileEdit.impl

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import feature.profileEdit.api.ProfileEditEntry
import feature.profileEdit.impl.ui.ProfileEditRoute
import javax.inject.Inject

class ProfileEditEntryImpl @Inject constructor() : ProfileEditEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileEditRoute(navController)
    }

    override val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?
        get() = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(300)
            )
        }


    override val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?
        get() = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(300)
            )
        }

}
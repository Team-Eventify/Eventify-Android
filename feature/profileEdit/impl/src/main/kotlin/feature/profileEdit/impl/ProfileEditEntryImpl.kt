package feature.profileEdit.impl

import feature.profileEdit.api.ProfileEditEntry

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
package feature.onboarding.impl

import feature.onboarding.api.OnBoardingEntry

class OnBoardingEntryImpl @Inject constructor() : OnBoardingEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        OnBoardingRoute(navController)
    }
}
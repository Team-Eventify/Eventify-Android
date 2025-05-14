package feature.onboarding.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.onboarding.api.OnBoardingEntry
import feature.onboarding.impl.ui.OnBoardingRoute
import javax.inject.Inject

class OnBoardingEntryImpl @Inject constructor() : OnBoardingEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        OnBoardingRoute(navController)
    }
}
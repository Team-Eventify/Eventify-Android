package feature.onboarding.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import feature.onboarding.api.OnBoardingEntry
import feature.onboarding.api.OnBoardingListener
import feature.onboarding.impl.OnBoardingViewModel
import feature.register.api.RegisterEntry
import uikit.components.topBar.LocalTopBarState


@Composable
fun OnBoardingRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<OnBoardingViewModel>()
    val topBarState = LocalTopBarState.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : OnBoardingListener {
        override fun flowNext() {
            viewModel.finishOnboarding()
            features.navigateNewTaskFeature<RegisterEntry, OnBoardingEntry>(navController)

        }
    }

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    OnBoardingScreen(listener)
}


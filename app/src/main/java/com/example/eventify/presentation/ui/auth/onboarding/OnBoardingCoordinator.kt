package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class OnBoardingCoordinator(
    val viewModel: OnBoardingViewModel
) {
    val screenStateFlow = viewModel.stateFlow


}

@Composable
fun rememberOnBoardingCoordinator(
    viewModel: OnBoardingViewModel = hiltViewModel()
): OnBoardingCoordinator {
    return remember(viewModel) {
        OnBoardingCoordinator(
            viewModel = viewModel
        )
    }
}
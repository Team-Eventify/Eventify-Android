package com.example.eventify.presentation.ui.account.aboutapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class AboutAppCoordinator(
    val viewModel: AboutAppViewModel
) {
    val screenStateFlow = viewModel.stateFlow


}

@Composable
fun rememberAboutAppCoordinator(
    viewModel: AboutAppViewModel = hiltViewModel()
): AboutAppCoordinator {
    return remember(viewModel) {
        AboutAppCoordinator(
            viewModel = viewModel
        )
    }
}
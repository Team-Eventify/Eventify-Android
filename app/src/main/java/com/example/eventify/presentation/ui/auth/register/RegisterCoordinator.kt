package com.example.eventify.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class RegisterCoordinator(
    val viewModel: RegisterViewModel
) {
    val screenStateFlow = viewModel.stateFlow

}

@Composable
fun rememberRegisterCoordinator(
    viewModel: RegisterViewModel = hiltViewModel()
): RegisterCoordinator {
    return remember(viewModel) {
        RegisterCoordinator(
            viewModel = viewModel
        )
    }
}
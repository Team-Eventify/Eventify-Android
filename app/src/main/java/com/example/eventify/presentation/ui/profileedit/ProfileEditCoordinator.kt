package com.example.eventify.presentation.ui.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProfileEditCoordinator(
    val viewModel: ProfileEditViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberProfileEditCoordinator(
    viewModel: ProfileEditViewModel = hiltViewModel()
): ProfileEditCoordinator {
    return remember(viewModel) {
        ProfileEditCoordinator(
            viewModel = viewModel
        )
    }
}
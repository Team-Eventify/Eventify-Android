package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ChooseCategoriesCoordinator(
    val viewModel: ChooseCategoriesViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberChooseCategoriesCoordinator(
    viewModel: ChooseCategoriesViewModel = hiltViewModel()
): ChooseCategoriesCoordinator {
    return remember(viewModel) {
        ChooseCategoriesCoordinator(
            viewModel = viewModel
        )
    }
}
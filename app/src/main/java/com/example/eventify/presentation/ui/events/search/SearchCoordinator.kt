package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class SearchCoordinator(
    val viewModel: SearchViewModel
) {
    val screenStateFlow = viewModel.stateFlow
}

@Composable
fun rememberSearchCoordinator(
    viewModel: SearchViewModel = hiltViewModel()
): SearchCoordinator {
    return remember(viewModel) {
        SearchCoordinator(
            viewModel = viewModel
        )
    }
}
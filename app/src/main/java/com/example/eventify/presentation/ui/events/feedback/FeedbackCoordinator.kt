package com.example.eventify.presentation.ui.events.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class FeedbackCoordinator(
    val viewModel: FeedbackViewModel
) {
    val screenStateFlow = viewModel.stateFlow


}

@Composable
fun rememberFeedbackCoordinator(
    viewModel: FeedbackViewModel = hiltViewModel()
): FeedbackCoordinator {
    return remember(viewModel) {
        FeedbackCoordinator(
            viewModel = viewModel
        )
    }
}
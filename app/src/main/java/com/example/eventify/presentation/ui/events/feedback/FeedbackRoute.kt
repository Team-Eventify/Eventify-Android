package com.example.eventify.presentation.ui.events.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


@Composable
fun FeedbackRoute(
    coordinator: FeedbackCoordinator = rememberFeedbackCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState(FeedbackState())
    val actions = rememberFeedbackActions(coordinator)

    FeedbackScreen(uiState, actions)
}


@Composable
fun rememberFeedbackActions(coordinator: FeedbackCoordinator): FeedbackActions {
    return remember(coordinator) {
        FeedbackActions(
            onChangePositiveFeedbackText = coordinator.viewModel::changePositiveFeedbackText,
            onChangeNegativeFeedbackText = coordinator.viewModel::changeNegativeFeedbackText,
            onChangeAdditionalFeedbackText = coordinator.viewModel::changeAdditionalFeedbackText,
            onSendForm = coordinator.viewModel::sendForm
        )
    }
}

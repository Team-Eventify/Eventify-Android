package com.example.eventify.presentation.ui.events.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar


@Composable
fun FeedbackRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: FeedbackCoordinator = rememberFeedbackCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(FeedbackState())

    // UI Actions
    val actions = rememberFeedbackActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            topBar = {
                DefaultTopAppBar(
                    title = "Feedback",
                    onNavigateUp = coordinator.viewModel::navigateUp
                )
            }
        )
    }

    // UI Rendering
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

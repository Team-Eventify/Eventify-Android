package com.example.eventify.presentation.ui.eventsfeed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun EventsFeedRoute(
    navController: NavHostController,
    coordinator: EventsFeedCoordinator = rememberEventsFeedCoordinator(navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actions = rememberEventsFeedActions(coordinator)

    // UI Rendering
    EventsFeedScreen(uiState, actions)
}


@Composable
fun rememberEventsFeedActions(coordinator: EventsFeedCoordinator): EventsFeedActions {
    return remember(coordinator) {
        EventsFeedActions(
            onLoadData = coordinator.viewModel::loadData,
            onRefreshData = coordinator.viewModel::refreshData,
            onEventClick = coordinator::navigateToEventDetail
        )
    }
}
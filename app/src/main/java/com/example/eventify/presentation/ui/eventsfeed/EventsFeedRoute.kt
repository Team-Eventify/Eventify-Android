package com.example.eventify.presentation.ui.eventsfeed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.eventsfeed.components.EventsFeedTopAppBar

@Composable
fun EventsFeedRoute(
    navController: NavHostController,
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: EventsFeedCoordinator = rememberEventsFeedCoordinator(navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actions = rememberEventsFeedActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            topBar = {
                EventsFeedTopAppBar()
            }
        )
    }

    // UI Rendering
    EventsFeedScreen(uiState, actions, coordinator.imageLoader)
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
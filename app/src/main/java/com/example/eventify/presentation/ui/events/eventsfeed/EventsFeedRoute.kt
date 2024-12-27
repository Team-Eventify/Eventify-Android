package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventsFeedTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
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

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            showBottomBar = true,
            topBar = {
                EventsFeedTopAppBar(
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
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
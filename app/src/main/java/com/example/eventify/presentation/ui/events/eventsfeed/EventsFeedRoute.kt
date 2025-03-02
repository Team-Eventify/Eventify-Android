package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.eventsfeed.components.LoadingEventFeed


@Composable
fun EventsFeedRoute(
    navController: NavHostController,
    coordinator: EventsFeedCoordinator = rememberEventsFeedCoordinator(navController)
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()
    val actions = rememberEventsFeedActions(coordinator)
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    when (uiState){
        is UiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_load_feed),
            description = (uiState as UiState.Error).message
        )
        UiState.Loading -> LoadingEventFeed()
        is UiState.ShowFeed -> {
            EventsFeedScreen(uiState as UiState.ShowFeed, actions)
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.events_feed_title),
                size = TopBarSize.SMALL,
            )
        )
    }

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
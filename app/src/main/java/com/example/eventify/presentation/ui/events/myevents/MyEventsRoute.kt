package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedEntry
import com.example.eventify.presentation.navigation.navigateNewTaskFeature
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.myevents.components.EmptyMyEventsScreen
import com.example.eventify.presentation.ui.events.myevents.components.LoadingMyEvents
import com.example.eventify.presentation.ui.events.myevents.state.MyEventsListener
import com.example.eventify.presentation.ui.events.myevents.state.UiState

@Composable
fun MyEventsRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<MyEventsViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : MyEventsListener {
        override fun onRefresh() {
            viewModel.refresh()
        }

        override fun navigateToEvent(eventId: String) {
            features.navigateToFeature<EventDetailEntry>(navController)
        }

        override fun navigateToFeedback(eventId: String) {
            TODO("Not yet implemented")
        }

        override fun navigateToFeed() {
            features.navigateNewTaskFeature<EventsFeedEntry, MyEventsEntry>(navController)
        }
    }

    // UI Rendering
    when (uiState) {
        UiState.Initial -> {
            LoadingMyEvents()
        }
        is UiState.Empty -> EmptyMyEventsScreen(
            onActionClick = listener::navigateToFeed,
        )
        is UiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_loading_my_events),
            description = (uiState as UiState.Error).message
        )
        is UiState.ShowMyEvents -> {
            MyEventsScreen(uiState as UiState.ShowMyEvents, listener)
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.my_events_title),
                size = TopBarSize.SMALL,
            )
        )
    }
}


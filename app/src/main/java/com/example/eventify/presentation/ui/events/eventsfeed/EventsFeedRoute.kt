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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.entries.events.EventDetailEntry
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.eventsfeed.components.LoadingEventFeed
import com.example.eventify.presentation.ui.events.eventsfeed.state.EventFeedListener
import com.example.eventify.presentation.ui.events.eventsfeed.state.UiState


@Composable
fun EventsFeedRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<EventsFeedViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features


    val listener = object : EventFeedListener {
        override fun onEventClick(eventId: String) {
            features.navigateToFeature<EventDetailEntry>(navController)
        }

        override fun onRefreshData() {
            viewModel.refreshData()
        }

    }

    when (uiState){
        is UiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_load_feed),
            description = (uiState as UiState.Error).message
        )
        UiState.Loading -> LoadingEventFeed()
        is UiState.ShowFeed -> {
            EventsFeedScreen(uiState as UiState.ShowFeed, listener)
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

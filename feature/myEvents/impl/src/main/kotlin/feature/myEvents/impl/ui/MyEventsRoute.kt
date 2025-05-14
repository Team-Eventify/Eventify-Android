package feature.myEvents.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.common.navigation.ARG_EVENT_ID
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateNewTaskFeature
import core.featureManager.navigateToFeature
import feature.eventDetail.api.EventDetailEntry
import feature.eventFeed.api.EventsFeedEntry
import feature.myEvents.api.MyEventsEntry
import feature.myEvents.api.MyEventsListener
import feature.myEvents.impl.MyEventsViewModel
import feature.myEvents.impl.components.EmptyMyEventsScreen
import feature.myEvents.impl.components.LoadingMyEvents
import feature.myEvents.impl.state.UiState
import uikit.components.screens.ErrorScreen
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import com.example.eventify.uikit.R as UiKitR


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
            features.navigateToFeature<EventDetailEntry>(navController) {
                path {
                    put(ARG_EVENT_ID, eventId)
                }
            }
        }

        override fun navigateToFeedback(eventId: String) {
            TODO("Not yet implemented")
        }

        override fun navigateToFeed() {
            features.navigateNewTaskFeature<EventsFeedEntry, MyEventsEntry>(navController)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadEvents()
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
            title = stringResource(UiKitR.string.error_loading_my_events),
            description = (uiState as UiState.Error).message
        )
        is UiState.ShowMyEvents -> {
            MyEventsScreen(uiState as UiState.ShowMyEvents, listener)
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(UiKitR.string.my_events_title),
                size = TopBarSize.SMALL,
            )
        )
    }
}


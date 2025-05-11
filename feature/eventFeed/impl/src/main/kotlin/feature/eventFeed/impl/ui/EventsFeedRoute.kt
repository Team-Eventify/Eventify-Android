package feature.eventFeed.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.common.navigation.ARG_EVENT_ID
import feature.eventDetail.api.EventDetailEntry
import feature.eventFeed.api.EventFeedListener
import feature.eventFeed.impl.EventsFeedViewModel
import feature.eventFeed.impl.components.LoadingEventFeed
import feature.eventFeed.impl.state.UiState
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateToFeature
import uikit.components.screens.ErrorScreen
import com.example.eventify.uikit.R as UiKitR

@Composable
internal fun EventsFeedRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<EventsFeedViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features


    val listener = object : EventFeedListener {
        override fun onEventClick(eventId: String) {
            features.navigateToFeature<EventDetailEntry>(navController) {
                path {
                    put(ARG_EVENT_ID, eventId)
                }
            }
        }

        override fun onRefreshData() {
            viewModel.refreshData()
        }

    }

    when (uiState){
        is UiState.Error -> ErrorScreen(
            title = stringResource(UiKitR.string.error_load_feed),
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
                title = context.getString(UiKitR.string.events_feed_title),
                size = TopBarSize.SMALL,
            )
        )
    }

}

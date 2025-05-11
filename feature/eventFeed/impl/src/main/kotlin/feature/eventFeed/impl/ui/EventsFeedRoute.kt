package feature.eventFeed.impl.ui

import android.R.attr.path
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
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.eventsfeed.state.EventFeedListener
import feature.eventDetail.api.EventDetailEntry
import feature.eventFeed.impl.EventsFeedViewModel
import feature.eventFeed.impl.components.LoadingEventFeed
import feature.eventFeed.impl.state.UiState


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
//            features.navigateToFeature<EventDetailEntry>(
//                navController,
//                ARG_EVENT_ID to eventId
//            )

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

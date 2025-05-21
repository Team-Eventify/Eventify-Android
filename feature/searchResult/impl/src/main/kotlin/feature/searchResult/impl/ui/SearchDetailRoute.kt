package feature.searchResult.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feature.searchResult.impl.components.EventsSearchDetail
import feature.searchResult.impl.state.SearchDetailListener
import core.common.navigation.ARG_EVENT_ID
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateToFeature
import feature.eventDetail.api.EventDetailEntry
import feature.searchResult.impl.state.SearchDetailUiState
import uikit.components.screens.ErrorScreen
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.TopBarAction
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import com.example.eventify.uikit.R as UiKitR


@Composable
fun SearchDetailRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<SearchDetailViewModel>()
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val features = LocalFeaturesProvider.current.features
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    val listener = object : SearchDetailListener {
        override fun onBackClick() {
            navController.popBackStack()
        }

        override fun onEventClick(eventId: String) {
            features.navigateToFeature<EventDetailEntry>(navController) {
                path {
                    put(ARG_EVENT_ID, eventId)
                }
            }
        }

    }

    when (state) {
        SearchDetailUiState.NotFound -> ErrorScreen(
            title = stringResource(UiKitR.string.not_found),
        )

        is SearchDetailUiState.Error -> ErrorScreen(
            title = stringResource(UiKitR.string.failed_to_load_data),
            description = (state as SearchDetailUiState.Error).message,
        )

        is SearchDetailUiState.ShowEvents -> EventsSearchDetail(
            state = state as SearchDetailUiState.ShowEvents,
            actions = listener,
        )

        SearchDetailUiState.Loading -> {}
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(UiKitR.string.search),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = UiKitR.drawable.ic_chevron_right,
                    onClick = listener::onBackClick
                )
            )
        )
    }
}
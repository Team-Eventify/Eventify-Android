package feature.search.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feature.search.impl.SearchViewModel
import core.common.navigation.ARG_CATEGORY_ID
import core.common.navigation.ARG_EVENT_ID
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateToFeature
import feature.eventDetail.api.EventDetailEntry
import feature.search.impl.state.CategoryId
import feature.search.impl.state.EventId
import feature.search.impl.state.SearchListener
import feature.search.impl.state.SearchMode
import feature.searchResult.api.SearchDetailEntry
import uikit.components.topBar.LocalTopBarState


@Composable
fun SearchRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : SearchListener {
        override fun changeSearchMode(mode: SearchMode) {
            viewModel.changeSearchMode(mode)
        }

        override fun onEventClick(eventId: EventId) {
            features.navigateToFeature<EventDetailEntry>(navController){
                path {
                    put(ARG_EVENT_ID, eventId)
                }
            }
        }

        override fun onCategoryClick(categoryId: CategoryId) {
            features.navigateToFeature<SearchDetailEntry>(navController) {
                query {
                    put(ARG_CATEGORY_ID, categoryId)
                }
            }
        }

        override fun onChangeSearchQuery(value: String) {
            viewModel.changeSearchQuery(value)
        }

        override fun search() = Unit

        override fun cleanSearch() {
            viewModel.cleanSearchQuery()
        }

        override fun refresh() {
            // TODO implement
        }

    }

    SearchScreen(
        state = uiState,
        listener = listener,
    )

    LaunchedEffect(Unit) {
        topBarState.hide()
    }
}


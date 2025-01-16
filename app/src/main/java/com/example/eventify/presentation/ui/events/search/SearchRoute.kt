package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.events.search.components.EventsSearchBar

@Composable
fun SearchRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: SearchCoordinator = rememberSearchCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberSearchActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            showBottomBar = true,
            topBar = {
                EventsSearchBar(
                    query = uiState.searchText,
                    onSearch = { actions.onSearch() },
                    onQueryChange = actions.onSearchTextChanged,
                    onActiveChange = actions.onChangeActiveSearchBar,
                    active = uiState.isActiveSearchBar,
                    onClearQuery = actions.onClearSearchText,
                    searchItems = uiState.categories,
                    onChangeCategoryFilterActive = actions.onChangeCategoryFilterActive
                )
            }
        )

    }

    // UI Rendering
    SearchScreen(uiState, actions, coordinator.viewModel.imageLoader)

}


@Composable
fun rememberSearchActions(coordinator: SearchCoordinator): SearchActions {
    return remember(coordinator) {
        SearchActions(
            onSearch = coordinator.viewModel::search,
            onSearchTextChanged = coordinator.viewModel::changeSearchText,
            onChangeActiveSearchBar = coordinator.viewModel::changeSearchBarActive,
            onClearSearchText = coordinator.viewModel::clearSearchText,
            onRefreshData = coordinator.viewModel::refresh,
            onChangeCategoryFilterActive = coordinator.viewModel::changeCategoryFilterActive,
            onClickEventItem = coordinator.viewModel::goToEventDetail
        )
    }
}
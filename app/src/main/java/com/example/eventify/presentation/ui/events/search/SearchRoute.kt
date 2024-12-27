package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.events.search.components.EventsSearchBar

@Composable
fun SearchRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: SearchCoordinator = rememberSearchCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(SearchState())

    // UI Actions
    val actions = rememberSearchActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            topBar = {
                EventsSearchBar(
                    query = uiState.searchText,
                    onSearch = { actions.onSearch() },
                    onQueryChange = actions.onSearchTextChanged,
                    onActiveChange = {},
                    active = uiState.isActiveSearchBar,
                    onClearQuery = actions.onClearSearchText
                )
            }
        )

    }

    // UI Rendering
    SearchScreen(uiState, actions)

}


@Composable
fun rememberSearchActions(coordinator: SearchCoordinator): SearchActions {
    return remember(coordinator) {
        SearchActions(
            onSearch = coordinator.viewModel::search,
            onSearchTextChanged = coordinator.viewModel::changeSearchText,
            onChangeActiveSearchBar = coordinator.viewModel::changeSearchBarActive,
            onToggleSearch = coordinator.viewModel::toggleSearchBar,
            onClearSearchText = coordinator.viewModel::clearSearchText
        )
    }
}
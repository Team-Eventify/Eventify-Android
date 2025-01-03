package com.example.eventify.presentation.ui.events.search

import com.example.eventify.domain.models.Category


/**
 * UI State that represents SearchScreen
 **/
data class SearchState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchText: String = "",
    val isActiveSearchBar: Boolean = false,
    val categories: List<Category> = emptyList()
)

/**
 * Search Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SearchActions(
    val onSearchTextChanged: (String) -> Unit = {},
    val onChangeActiveSearchBar: (Boolean) -> Unit = {},
    val onSearch: () -> Unit = {},
    val onToggleSearch: () -> Unit = {},
    val onClearSearchText: () -> Unit = {},
    val onRefreshData: () -> Unit = {}

)
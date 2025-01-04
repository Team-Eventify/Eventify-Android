package com.example.eventify.presentation.ui.events.search

import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents SearchScreen
 **/
data class SearchState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchText: String = "",
    val isActiveSearchBar: Boolean = false,
    val categories: List<CategorySelectItem> = emptyList(),
    val searchedEvents: List<ShortEventItem> = emptyList()
)

/**
 * Search Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SearchActions(
    val onSearchTextChanged: (String) -> Unit = {},
    val onChangeActiveSearchBar: (Boolean) -> Unit = {},
    val onSearch: () -> Unit = {},
    val onClearSearchText: () -> Unit = {},
    val onRefreshData: () -> Unit = {},
    val onChangeCategoryFilterActive: (String, Boolean) -> Unit = {_, _ ->},
    val onClickEventItem: (String) -> Unit = {}
)
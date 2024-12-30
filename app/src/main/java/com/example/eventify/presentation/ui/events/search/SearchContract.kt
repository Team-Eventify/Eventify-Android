package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.eventify.data.models.CategoryInfo


/**
 * UI State that represents SearchScreen
 **/
data class SearchState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchText: String = "",
    val isActiveSearchBar: Boolean = false,
    val categories: List<CategoryInfo> = emptyList()
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
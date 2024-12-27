package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents SearchScreen
 **/
data class SearchState(
    val searchText: String = "",
    val isActiveSearchBar: Boolean = false
)

/**
 * Search Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SearchActions(
    val onSearchTextChanged: (String) -> Unit = {},
    val onChangeActiveSearchBar: (Boolean) -> Unit = {},
    val onSearch: () -> Unit = {}
)
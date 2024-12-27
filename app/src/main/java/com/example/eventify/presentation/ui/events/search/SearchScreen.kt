package com.example.eventify.presentation.ui.events.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    actions: SearchActions,
) {
    Column {
        SearchBar(
            query = state.searchText,
            onQueryChange = actions.onSearchTextChanged,
            onSearch = {
                actions.onSearch()
           },
            active = state.isActiveSearchBar,
            onActiveChange = actions.onChangeActiveSearchBar
        ) {

        }
    }
}

@Composable
@Preview(name = "Search")
private fun SearchScreenPreview() {
    SearchScreen(
        state = SearchState(),
        actions = SearchActions()
    )
}


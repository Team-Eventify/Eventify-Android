package com.example.eventify.presentation.ui.events.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.shared.CategoryCard
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SearchScreen(
    state: SearchState,
    actions: SearchActions,
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefreshData
    ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.categories, key = {it.id}){ category ->
                CategoryCard(category = category)
            }
        }
    }
}

@Composable
@Preview(name = "Search", showBackground = true, showSystemUi = true)
private fun SearchScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            SearchScreen(
                state = SearchState(),
                actions = SearchActions()
            )
        }
    }
}


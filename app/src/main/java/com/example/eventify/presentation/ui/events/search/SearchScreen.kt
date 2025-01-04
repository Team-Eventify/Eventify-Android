package com.example.eventify.presentation.ui.events.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.UUID

@Composable
fun SearchScreen(
    state: SearchState,
    actions: SearchActions,
    imageLoader: ImageLoader
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)
    val selectedCategories = remember(state.categories) { state.categories.filter { it.selected } }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefreshData
    ){
        Column(

        ) {
            CategorySelector(
                categories = selectedCategories,
                onClickCategory = actions.onChangeCategoryFilterActive
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = state.searchedEvents,
                    key = { it.id }
                ){ event ->
                    EventCard(
                        event = event,
                        onClick = actions.onClickEventItem,
                        imageLoader = imageLoader
                    )
                }
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
                state = SearchState(
                    categories = List(8){
                        CategorySelectItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(2).values.toList().joinToString()
                        )
                    }
                ),
                actions = SearchActions(),
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}


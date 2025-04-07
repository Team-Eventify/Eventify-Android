package com.example.eventify.presentation.ui.events.search

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.presentation.ui.common.CategoryCard
import com.example.eventify.presentation.ui.events.search.components.CategoriesSearch
import com.example.eventify.presentation.ui.events.search.components.EventsSearch
import com.example.eventify.presentation.ui.events.search.components.FailedSearch
import com.example.eventify.presentation.ui.events.search.state.CategoryId
import com.example.eventify.presentation.ui.events.search.state.EventId
import com.example.eventify.presentation.ui.events.search.state.SearchListener
import com.example.eventify.presentation.ui.events.search.state.SearchResult
import com.example.eventify.presentation.ui.events.search.state.SearchMode
import com.example.eventify.presentation.ui.events.search.state.SearchUiState
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions

@Composable
fun SearchScreen(
    state: SearchUiState,
    listener: SearchListener,
) {
    val dimentions = LocalDimentions.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimentions.windowPaddings)
    ) {
        SegmentedTabSwitcher(
            tabs = SearchMode.entries,
            onTabClick = listener::changeSearchMode,
            currentTab = state.searchMode,
        )

        when (state.searchResult) {
            is SearchResult.Categories -> {
                CategoriesSearch(
                    categories = state.searchResult.items,
                    onCategoryClick = listener::onCategoryClick,
                )
            }
            is SearchResult.Error -> {
                FailedSearch(
                    message = state.searchResult.message,
                )
            }
            is SearchResult.Events -> {
                EventsSearch(
                    events = state.searchResult.items,
                    onEventClick = listener::onEventClick,
                )
            }
            SearchResult.None -> {}
        }
    }



}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedTabSwitcher(
    tabs: List<SearchMode>,
    currentTab: SearchMode,
    onTabClick: (SearchMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SearchMode.entries.forEachIndexed { index, tab ->
            SegmentedButton(
                selected = tab == currentTab,
                onClick = {
                    onTabClick(tab)
                },
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = tabs.size,
                ),
                label = {
                    Text(
                        text = stringResource(tab.labelResId)
                    )
                }
            )

        }
    }
}

@Composable
@Preview(name = "Search", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SearchScreenPreview() {
    EventifyTheme  {
        SearchScreen(
            state = SearchUiState(
                searchMode = SearchMode.Events,
                searchResult = SearchResult.Events(
                    items = emptyList()
                ),
                searchText = ""
            ),
            listener = object : SearchListener {
                override fun changeSearchMode(mode: SearchMode) = Unit
                override fun onEventClick(eventId: EventId) {
                    TODO("Not yet implemented")
                }

                override fun onCategoryClick(categoryId: CategoryId) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}


package feature.search.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import feature.search.impl.components.CategoriesSearch
import feature.search.impl.components.EventsSearch
import feature.search.impl.components.FailedSearch
import feature.search.impl.state.CategoryId
import feature.search.impl.state.EventId
import feature.search.impl.state.SearchListener
import feature.search.impl.state.SearchResult
import feature.search.impl.state.SearchMode
import feature.search.impl.state.SearchUiState
import uikit.LocalDimentions
import uikit.components.topBar.SearchTopBar
import uikit.space10
import uikit.space32
import uikit.EventifyTheme

@Composable
fun SearchScreen(
    state: SearchUiState,
    listener: SearchListener,
) {
    val dimentions = LocalDimentions.current

    Column(
        verticalArrangement = Arrangement.spacedBy(space10, Alignment.Top),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimentions.windowPaddings)
    ) {

        SearchTopBar(
            value = state.searchText,
            onValueChanged = listener::onChangeSearchQuery,
            onTrailingIconClick = listener::cleanSearch,
            onLeadingIconClick = listener::search,
            onSearch = listener::search
        )

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
                    onRefresh = listener::refresh,
                )
            }
            is SearchResult.Events -> {
                EventsSearch(
                    events = state.searchResult.items,
                    onEventClick = listener::onEventClick,
                )
            }
            SearchResult.Empty -> {
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
            .height(space32)
    ) {
        SearchMode.entries.forEachIndexed { index, tab ->
            SegmentedButton(
                selected = tab == currentTab,
                onClick = {
                    onTabClick(tab)
                },
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    inactiveContainerColor = Color.Transparent,
                    activeBorderColor = Color.Transparent,
                    inactiveBorderColor = MaterialTheme.colorScheme.surfaceContainer,
                    activeContentColor = MaterialTheme.colorScheme.primary,
                ),
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = tabs.size,
                    baseShape = RoundedCornerShape(space10)
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
@Preview(name = "Search", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
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
                override fun onEventClick(eventId: EventId) = Unit
                override fun onCategoryClick(categoryId: CategoryId) = Unit
                override fun onChangeSearchQuery(value: String) = Unit
                override fun search() = Unit
                override fun cleanSearch() = Unit
                override fun refresh() = Unit
            }
        )
    }
}


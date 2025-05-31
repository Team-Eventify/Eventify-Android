package feature.search.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import feature.search.impl.components.CategoriesSearch
import feature.search.impl.components.EventsSearch
import feature.search.impl.components.InitialSearch
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
import uikit.components.screens.ErrorScreen
import uikit.components.screens.NothingFound
import uikit.space16
import uikit.space8
import com.example.eventify.feature.search.impl.R as SearchR
import com.example.eventify.uikit.R as UiKitR


@Composable
fun SearchScreen(
    state: SearchUiState,
    listener: SearchListener,
) {
    val dims = LocalDimentions.current
    val searchPlaceholder = when (state.searchMode) {
        SearchMode.Categories -> stringResource(SearchR.string.search_categories_placeholder)
        SearchMode.Events -> stringResource(SearchR.string.search_events_placeholder)
    }

    Scaffold(
        topBar = {
            Column(
                verticalArrangement = Arrangement.spacedBy(space10),
                modifier = Modifier
                    .padding(horizontal = space16)
                    .padding(bottom = space8)
            ) {
                SearchTopBar(
                    value = state.searchText,
                    onValueChanged = listener::onChangeSearchQuery,
                    onTrailingIconClick = listener::cleanSearch,
                    placeholder = searchPlaceholder,
                    onLeadingIconClick = listener::search,
                    onSearch = listener::search
                )

                SegmentedTabSwitcher(
                    tabs = SearchMode.entries,
                    onTabClick = listener::changeSearchMode,
                    currentTab = state.searchMode,
                )
            }
        }
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPaddings.calculateTopPadding())
                .padding(horizontal = space16)
            ,
        ) {
            when (state.searchResult) {
                is SearchResult.Categories -> {
                    CategoriesSearch(
                        categories = state.searchResult.items,
                        onCategoryClick = listener::onCategoryClick,
                        modifier = Modifier


                    )
                }
                is SearchResult.Error -> {
                    ErrorScreen(
                        title = stringResource(UiKitR.string.failed_to_load_data)
                    )
                }
                is SearchResult.Events -> {
                    EventsSearch(
                        events = state.searchResult.items,
                        onEventClick = listener::onEventClick,
                    )
                }
                SearchResult.Empty -> {
                    NothingFound()
                }
                SearchResult.Initial -> {
                    InitialSearch()
                }
            }
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
                border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.surfaceContainerHighest),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                    inactiveContainerColor = Color.Transparent,
                    activeBorderColor = Color.Transparent,
                    inactiveBorderColor = MaterialTheme.colorScheme.surfaceContainerHighest,
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
private fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewProvider::class) state: SearchUiState
) {
    EventifyTheme  {
        SearchScreen(
            state = state,
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


class SearchScreenPreviewProvider : PreviewParameterProvider<SearchUiState> {
    override val values: Sequence<SearchUiState> = sequenceOf(
        SearchUiState(
            searchMode = SearchMode.Events,
            searchResult = SearchResult.Categories(
                items = emptyList()
            ),
            searchText = ""
        ),
        SearchUiState(
            searchMode = SearchMode.Events,
            searchResult = SearchResult.Events(
                items = emptyList()
            ),
            searchText = ""
        ),
        SearchUiState(
            searchMode = SearchMode.Events,
            searchResult = SearchResult.Events(
                items = emptyList()
            ),
            searchText = "test text",
        ),
        SearchUiState(
            searchMode = SearchMode.Events,
            searchResult = SearchResult.Error(),
            searchText = ""
        ),
        SearchUiState(
            searchMode = SearchMode.Events,
            searchResult = SearchResult.Empty,
            searchText = ""
        ),
        SearchUiState(
            searchMode = SearchMode.Events,
            searchResult = SearchResult.Initial,
            searchText = ""
        )
    )
}


package com.example.eventify.presentation.ui.events.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.theme.EventifyTheme
import java.util.UUID
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    searchItems: List<CategorySelectItem>,
    onChangeCategoryFilterActive: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { onSearch(it) },
        active = active,
        onActiveChange = onActiveChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .then(modifier),
        shape = RoundedCornerShape(10.dp),
        leadingIcon = {
            Icon(painter = painterResource(R.drawable.ic_search), contentDescription = null)
        },
        trailingIcon = {
            if (active || query.isNotBlank() || query.isNotEmpty()) {
                IconButton(onClick = onClearQuery) {
                    Icon(painter = painterResource(R.drawable.round_close_24), contentDescription = null)
                }
            }

        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        placeholder = {
            Text(text = stringResource(R.string.search))
        },
    ) {
        val items = remember(searchItems) {
            searchItems.filter { it.isShow }.sortedBy { it.selected }.reversed()
        }

        CategorySelector(
            categories = items,
            onClickCategory = onChangeCategoryFilterActive,
        )
    }
}

@Preview(name = "EventsSearchBar", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEventsSearchBarDark() {
    EventifyTheme(darkTheme = true) {
        Scaffold(
            topBar = {
                EventsSearchBar(
                    query = "",
                    onSearch = {},
                    onQueryChange = {},
                    onActiveChange = {},
                    active = false,
                    onClearQuery = {},
                    searchItems = List(6){
                        CategorySelectItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(2).values.toList().joinToString(),
                            color = Color.Cyan
                        )
                    },
                    onChangeCategoryFilterActive = {_, _ ->}
                )
            }
        ) { _ ->

        }
    }
}

@Preview(name = "EventsSearchBar", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEventsSearchBarExpandedDark() {
    EventifyTheme(darkTheme = true) {
        Scaffold(
            topBar = {
                EventsSearchBar(
                    query = "",
                    onSearch = {},
                    onQueryChange = {},
                    onActiveChange = {},
                    active = true,
                    onClearQuery = {},
                    searchItems = List(6){
                        CategorySelectItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(2).values.toList().joinToString(),
                            selected = Random.nextBoolean(),
                            color = Color.Cyan
                        )
                    },
                    onChangeCategoryFilterActive = {_, _ ->}
                )
            }
        ) { _ ->

        }
    }
}
package com.example.eventify.presentation.ui.events.search.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.example.eventify.R
import com.example.eventify.domain.models.Category
import com.example.eventify.presentation.models.ShortEventItem


@Stable
data class SearchUiState(
    val searchText: String,
    val searchResult: SearchResult,
    val searchMode: SearchMode,
)


enum class SearchMode(
    @StringRes val labelResId: Int,
) {
    Events(R.string.events),
    Categories(R.string.categories),
}

sealed class SearchResult {
    data class Events(
        val items: List<ShortEventItem> = emptyList(),
    ) : SearchResult()
    data class Categories(
        val items: List<Category> = emptyList(),
    ) : SearchResult()
    data class Error(
        val message: String,
    ) : SearchResult()
    data object None : SearchResult()
}
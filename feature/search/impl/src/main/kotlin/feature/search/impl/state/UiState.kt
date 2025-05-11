package feature.search.impl.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import data.models.Category
import domain.models.ShortEventItem
import com.example.eventify.uikit.R as UiKitR

@Stable
data class SearchUiState(
    val searchText: String,
    val searchResult: SearchResult,
    val searchMode: SearchMode,
)

// Order is important to preview
enum class SearchMode(
    @StringRes val labelResId: Int,
) {
    Categories(UiKitR.string.categories),
    Events(UiKitR.string.events),
}

sealed class SearchResult {
    data class Events(
        val items: List<ShortEventItem> = emptyList(),
    ) : SearchResult()
    data class Categories(
        val items: List<Category> = emptyList(),
    ) : SearchResult()
    data class Error(
        val message: String? = null,
    ) : SearchResult()
    data object None : SearchResult()
    data object Empty : SearchResult()
}
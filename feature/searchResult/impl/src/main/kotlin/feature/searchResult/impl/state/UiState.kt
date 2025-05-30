package feature.searchResult.impl.state

import data.models.Category
import domain.models.ShortEventItem


sealed class SearchDetailUiState {
    data object Loading : SearchDetailUiState()
    data object NotFound : SearchDetailUiState()
    data class Error(
        val message: String? = null
    ) : SearchDetailUiState()
    data class ShowEvents(
        val searchData: SearchData? = null,
        val items: List<ShortEventItem> = emptyList()
    ) : SearchDetailUiState() {
        val size: Int
            get() = items.size
    }
}


data class SearchData(
    val category: Category? = null
)
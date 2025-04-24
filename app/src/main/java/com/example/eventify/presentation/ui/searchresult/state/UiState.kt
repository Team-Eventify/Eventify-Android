package com.example.eventify.presentation.ui.searchresult.state

import com.example.eventify.domain.models.Category
import com.example.eventify.presentation.models.ShortEventItem


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
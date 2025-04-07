package com.example.eventify.presentation.ui.events.search

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.navigation.ARG_SEARCH_MODE
import com.example.eventify.presentation.navigation.ARG_SEARCH_TEXT
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.ui.events.search.state.SearchMode
import com.example.eventify.presentation.ui.events.search.state.SearchResult
import com.example.eventify.presentation.ui.events.search.state.SearchUiState
import com.example.eventify.presentation.utils.asText
import com.example.eventify.presentation.utils.toColor
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getEventsUseCase: GetEventsUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val searchText = savedStateHandle.get<String?>(ARG_SEARCH_TEXT) ?: ""
//    private val searchMode = savedStateHandle.get<String?>(ARG_SEARCH_MODE)?.let {
//        SearchMode.valueOf(it)
//    }

    private val _searchResultStateFlow = MutableStateFlow<SearchResult>(SearchResult.None)
    private val _searchModeStateFlow = MutableStateFlow(SearchMode.Events)

    val stateFlow = combine(
        _searchModeStateFlow,
        _searchResultStateFlow,
    ) { mode, result ->

        val searchResult = when (mode) {
            SearchMode.Events -> fetchEvents()
            SearchMode.Categories -> fetchCategories()
        }
        SearchUiState(
            searchText = searchText,
            searchResult = searchResult,
            searchMode = mode,
        )
    }
//        .onStart {
//
//        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SearchUiState(
                searchText = searchText,
                searchResult = SearchResult.None,
                searchMode = SearchMode.Events,
            )
        )


    private suspend fun fetchEvents(): SearchResult {
        return when (val eventsResult = getEventsUseCase()) {
            is Result.Error -> SearchResult.Error(
                message = eventsResult.asText(context)
            )
            is Result.Success -> {
                SearchResult.Events(
                    items = eventsResult.data.map {
                        it.toShortEventItem()
                    }
                )
            }
        }
    }

    private suspend fun fetchCategories(): SearchResult {
        return when (val categoriesResult = getCategoriesUseCase()) {
            is Result.Error -> SearchResult.Error(
                message = categoriesResult.asText(context)
            )
            is Result.Success -> {
                SearchResult.Categories(
                    items = categoriesResult.data
                )
            }
        }
    }

    fun changeSearchMode(mode: SearchMode) {
        viewModelScope.launch {
            _searchModeStateFlow.update {
                mode
            }
        }
    }
}
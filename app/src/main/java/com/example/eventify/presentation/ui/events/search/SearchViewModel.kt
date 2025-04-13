package com.example.eventify.presentation.ui.events.search

import android.content.Context
import androidx.compose.runtime.currentCompositionErrors
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
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

    private val sharedSearchQuery = savedStateHandle[ARG_SEARCH_TEXT] ?: ""
    private val _searchQueryStateFlow = MutableStateFlow(sharedSearchQuery)
    private val _searchResultStateFlow = MutableStateFlow<SearchResult>(SearchResult.None)
    private val _searchModeStateFlow = MutableStateFlow(SearchMode.Events)

    val stateFlow = combine(
        _searchModeStateFlow,
        _searchResultStateFlow,
        _searchQueryStateFlow,
    ) { mode, result, query ->
        SearchUiState(
            searchText = query,
            searchResult = result,
            searchMode = mode,
        )
    }
        .distinctUntilChanged()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SearchUiState(
                searchText = sharedSearchQuery,
                searchResult = SearchResult.None,
                searchMode = SearchMode.Events,
            )
        )

    init {
        viewModelScope.launch {
            combine(
                _searchModeStateFlow,
                _searchQueryStateFlow,
            ) { mode, query ->
                Pair(mode, query)
            }
            .debounce(200)
            .collectLatest { (mode, query) ->
                _searchResultStateFlow.update {
                    when (mode) {
                        SearchMode.Events -> fetchEvents(query)
                        SearchMode.Categories -> fetchCategories(query)
                    }
                }
            }
        }
    }


    private suspend fun fetchEvents(query: String): SearchResult {
        val queryFilter = EventsFilterData(
            title = query
        ).takeIf { query.isNotEmpty() }

        return when (val eventsResult = getEventsUseCase(filter = queryFilter)) {
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

    private suspend fun fetchCategories(query: String): SearchResult {
        return when (val categoriesResult = getCategoriesUseCase()) {
            is Result.Error -> SearchResult.Error(
                message = categoriesResult.asText(context)
            )
            is Result.Success -> {
                SearchResult.Categories(
                    items = categoriesResult.data.filter { it.title.contains(query, ignoreCase = true) }
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

    fun changeSearchQuery(value: String) {
        viewModelScope.launch {
            _searchQueryStateFlow.update { value }
        }
    }

    fun cleanSearchQuery() {
        viewModelScope.launch {
            _searchQueryStateFlow.update { "" }
        }
    }
}
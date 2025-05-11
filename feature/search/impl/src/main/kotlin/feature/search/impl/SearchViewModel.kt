package feature.search.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import core.common.BaseViewModel
import core.common.extentions.isNotFound
import core.common.extentions.isServerError
import core.common.navigation.ARG_SEARCH_TEXT
import feature.search.impl.state.SearchMode
import feature.search.impl.state.SearchResult
import feature.search.impl.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import data.remote.models.events.EventsFilterData
import domain.categories.GetCategoriesUseCase
import domain.events.GetEventsUseCase
import domain.extentions.isHidden
import domain.models.toShort
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getEventsUseCase: GetEventsUseCase,
) : BaseViewModel() {

    private val sharedSearchQuery = savedStateHandle[ARG_SEARCH_TEXT] ?: ""
    private val _searchQueryStateFlow = MutableStateFlow(sharedSearchQuery)
    private val _searchResultStateFlow = MutableStateFlow<SearchResult>(SearchResult.None)
    private val _searchModeStateFlow = MutableStateFlow(SearchMode.Categories)

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
//        .distinctUntilChanged()
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
        launchCatching {
            combine(
                _searchModeStateFlow,
                _searchQueryStateFlow.debounce(200),
            ) { mode, query -> mode to query }
            .collectLatest { (mode, query) ->
                when (mode) {
                    SearchMode.Events -> fetchEvents(query)
                    SearchMode.Categories -> fetchCategories(query)
                }
            }
        }
    }


    private fun fetchEvents(query: String) {
        launchCatching(catch = ::handleErrors) {
            val queryFilter = EventsFilterData(
                title = query
            ).takeIf { query.isNotEmpty() }

            _searchResultStateFlow.update {
                SearchResult.Events(
                    items = getEventsUseCase(queryFilter)
                        .filter { !it.state.isHidden() }
                        .map {
                            it.toShort()
                        }
                )
            }

        }
    }

    private fun fetchCategories(query: String) {
        launchCatching(catch = ::handleErrors) {
            _searchResultStateFlow.update {
                getCategoriesUseCase()
                    .filter {
                        it.title.contains(query, ignoreCase = true)
                    }
                    .takeIf { it.isNotEmpty() }?.let { categories ->
                        SearchResult.Categories(items = categories)
                    } ?: SearchResult.Empty

            }
        }
    }

    fun changeSearchMode(mode: SearchMode) {
        viewModelScope.launch {
            _searchModeStateFlow.update { mode }
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

    private fun handleErrors(error: Throwable) {
        when {
            error.isServerError() -> _searchResultStateFlow.update { SearchResult.Error() }

            error.isNotFound() -> _searchResultStateFlow.update { SearchResult.Empty }

            else -> _searchResultStateFlow.update { SearchResult.Error() }
        }
    }
}
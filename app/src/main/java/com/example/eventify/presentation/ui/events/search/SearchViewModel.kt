package com.example.eventify.presentation.ui.events.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import coil3.ImageLoader
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.HomeRouter
import com.example.eventify.presentation.navigation.navgraphs.MainNavHost
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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
    private val navigator: Navigator,
    val imageLoader: ImageLoader
) : ViewModel() {

    private val sharedQuery = savedStateHandle.toRoute<HomeRouter.Search>().query
    private val _stateFlow: MutableStateFlow<SearchState> = MutableStateFlow(SearchState(
        searchText = sharedQuery ?: ""
    ))
    val stateFlow: StateFlow<SearchState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SearchState(searchText = sharedQuery ?: "")
        )

    private suspend fun loadSearchData(){
        when (val result = getCategoriesUseCase()){
            is Result.Error -> {
                SnackbarController.sendEvent(
                    SnackbarEvent(message = result.error.toString())
                )
            }
            is Result.Success -> {
                _stateFlow.update { currentState ->
                    currentState.copy(
                        categories = result.data.map {
                            CategorySelectItem(
                                id = it.id,
                                title = it.title,
                                selected = false
                            )
                        }
                    )
                }
            }
        }
    }

    private suspend fun searchEvents(){
        val filterData = stateFlow.value.categories.let { categories ->
            EventsFilterData(
                categoryIds = categories.filter { it.selected }.map { it.id }
            )
        }
        when (val response = getEventsUseCase(filter = filterData)){
            is Result.Error -> TODO()
            is Result.Success -> {
                _stateFlow.update { currentState ->
                    currentState.copy(
                        searchedEvents = response.data.map { it.toShortEventItem() }
                    )
                }
            }
        }
    }

    fun changeCategoryFilterActive(categoryId: String, value: Boolean){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    categories = currentState.categories.map { category ->
                        if (category.id == categoryId) {
                            category.copy(selected = value)
                        } else {
                            category
                        }
                    }
                )
            }
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true) }
            loadSearchData()
            _stateFlow.update { it.copy(isLoading = false) }
        }
    }

    fun search(){
        Timber.d("On Search")
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    isActiveSearchBar = false
                )
            }
            searchEvents()
        }
    }

    fun changeSearchText(value: String){
        Timber.d("On ChangeSearchText")

        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    searchText = value,
                    categories = currentState.categories.map { category -> category.copy(isShow = category.title.contains(value))}
                )
            }
        }
    }

    fun changeSearchBarActive(value: Boolean){
        Timber.d("On ChangeSearchBarActive")

        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    isActiveSearchBar = value
                )
            }
        }
    }

    fun clearSearchText(){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    isActiveSearchBar = false,
                    searchText = ""
                )
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _stateFlow.update { it.copy(isRefreshing = true) }
            searchEvents()
            _stateFlow.update { it.copy(isRefreshing = false) }
        }
    }

    fun goToEventDetail(eventId: String){
        viewModelScope.launch {
            navigator.navigate(RootRouter.EventDetailRoute(eventId))
        }
    }
}
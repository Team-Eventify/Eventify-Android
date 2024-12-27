package com.example.eventify.presentation.ui.events.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.presentation.navigation.navgraphs.HomeRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val sharedQuery = savedStateHandle.toRoute<HomeRouter.Search>().query
    private val _stateFlow: MutableStateFlow<SearchState> = MutableStateFlow(SearchState(
        searchText = sharedQuery ?: ""
    ))
    val stateFlow: StateFlow<SearchState> = _stateFlow.asStateFlow()

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    categories = try {
                        getCategoriesUseCase()
                    } catch (e: Exception){
                        emptyList()
                    }
                )
            }
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
        }
    }

    fun changeSearchText(value: String){
        Timber.d("On ChangeSearchText")

        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    searchText = value,
                    isActiveSearchBar = true
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

    fun toggleSearchBar(){
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                currentState.copy(
                    isActiveSearchBar = !currentState.isActiveSearchBar
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
}
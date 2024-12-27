package com.example.eventify.presentation.ui.events.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val stateFlow: StateFlow<SearchState> = _stateFlow.asStateFlow()

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
}
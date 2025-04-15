package com.example.eventify.presentation.ui.searchresult

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.navigation.ARG_CATEGORY_ID
import com.example.eventify.presentation.ui.searchresult.state.SearchData
import com.example.eventify.presentation.ui.searchresult.state.SearchDetailUiState
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


private data class SearchNavArgs(
    val categoryId: String? = null,
) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle): SearchNavArgs {
            return SearchNavArgs(
                categoryId = savedStateHandle[ARG_CATEGORY_ID]
            )
        }
    }
}




@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventsUseCase: GetEventsUseCase,
    private val categoryRepository: CategoryRepository,
    @ApplicationContext private val context: Context,
) : BaseViewModel() {
    private val navArgs = SearchNavArgs.from(savedStateHandle)

    private val mutableStateFlow = MutableStateFlow<SearchDetailUiState>(SearchDetailUiState.Loading)
    val stateFlow = mutableStateFlow
        .onStart {
            load()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SearchDetailUiState.Loading
        )

    private fun load() {
        val filter = EventsFilterData(
            categoryIds = navArgs.categoryId?.let { listOf(it) }
        )

        launchCatching(
            catch = {
                // TODO
            }
        ) {
            mutableStateFlow.update {
                val searchedCategory = navArgs.categoryId?.let { categoryId ->
                    categoryRepository.readCategory(categoryId)
                }

                val events = getEventsUseCase(filter)

                SearchDetailUiState.ShowEvents(
                    items = events
                        .map {
                            it.toShortEventItem()
                        },
                    searchData = SearchData(
                        category = searchedCategory
                    )
                )
            }
        }

    }
}
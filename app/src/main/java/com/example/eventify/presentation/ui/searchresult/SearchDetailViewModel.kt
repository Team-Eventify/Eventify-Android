package com.example.eventify.presentation.ui.searchresult

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.remote.models.events.EventsFilterData
import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.toShortEventItem
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.navigation.ARG_CATEGORY_ID
import com.example.eventify.presentation.ui.searchresult.state.SearchData
import com.example.eventify.presentation.ui.searchresult.state.SearchDetailUiState
import com.example.eventify.presentation.utils.asText
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
) : ViewModel() {
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

    private suspend fun load() {
        val filter = EventsFilterData(
            categoryIds = navArgs.categoryId?.let { listOf(it) }
        )
        mutableStateFlow.update {
            val searchedCategory = navArgs.categoryId?.let { categoryId ->
                 when (val categoryResult = categoryRepository.readCategory(categoryId)){
                    is Result.Error -> null
                    is Result.Success -> categoryResult.data
                }
            }


            when (val result = getEventsUseCase(filter)) {
                is Result.Error -> when (result.error) {
                    DataError.Network.NOT_FOUND -> {
                        SearchDetailUiState.NotFound
                    }

                    else -> SearchDetailUiState.Error(
                        message = result.asText(context)
                    )
                }
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        return@update SearchDetailUiState.NotFound
                    }

                    SearchDetailUiState.ShowEvents(
                        items = result.data.map { it.toShortEventItem() },
                        searchData = SearchData(
                            category = searchedCategory
                        )
                    )
                }
            }
        }
    }
}
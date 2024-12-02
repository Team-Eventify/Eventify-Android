package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.domain.di.MockedEventsRepository
import com.example.eventify.presentation.models.EventFeedResult
import com.example.eventify.presentation.models.EventFeedUiState
import com.example.eventify.presentation.models.ShortEventItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val categoriesRepository: CategoryRepository
) : ViewModel() {
    var events by mutableStateOf(emptyList<ShortEventItem>())
        private set

    var categories by mutableStateOf(emptyList<CategoryInfo>())
        private set

    var result by mutableStateOf<EventFeedResult>(EventFeedResult.Idle)
        private set

    var uiState by mutableStateOf(EventFeedUiState.default())
        private set

    init {
        loadData()
    }

    private suspend fun _loadEvents() {
        val eventsResponse = eventsRepository.getEventsList()
        events = eventsResponse.map {
            ShortEventItem(
                id = it.id,
                title = it.title,
                description = it.description,
                start = it.start,
                end = it.end
            )
        }
    }

    private suspend fun _loadCategories() {
        val _categories = categoriesRepository.getCategoriesList()
        categories = _categories
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                result = EventFeedResult.Loading
                _loadEvents()
                _loadCategories()
                result = EventFeedResult.Success
            } catch (e: Exception) {
                result = EventFeedResult.Error(e.message ?: "Ошибка")
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            try {
                result = EventFeedResult.Refreshing
                _loadEvents()
                _loadCategories()
                result = EventFeedResult.Success
            } catch (e: Exception) {
                result = EventFeedResult.Error(e.message ?: "Ошибка")
            }
        }
    }

}
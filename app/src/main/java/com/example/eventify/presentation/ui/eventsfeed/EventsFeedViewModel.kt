package com.example.eventify.presentation.ui.eventsfeed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.models.ShortEventItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EventsFeedViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<EventsFeedState> = MutableStateFlow(EventsFeedState.default())
    val stateFlow: StateFlow<EventsFeedState> = _stateFlow.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            val categories = getCategoriesUseCase()
            val events = getEventsUseCase()

            _stateFlow.update { curentState ->
                curentState.copy(
                    events = events.map { ShortEventItem(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        start = it.start,
                        end = it.end
                    ) },
                    categories = categories
                )
            }
        }
    }

    fun refreshData() {

    }

}
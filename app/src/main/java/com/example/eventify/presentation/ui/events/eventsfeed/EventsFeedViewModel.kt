package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.ImageLoader
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.domain.usecases.events.GetEventsUseCase
import com.example.eventify.presentation.models.ShortEventItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EventsFeedViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    val imageLoader: ImageLoader
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<EventsFeedState> = MutableStateFlow(EventsFeedState.default())
    val stateFlow: StateFlow<EventsFeedState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EventsFeedState.default()
        )

    fun loadData() {
        viewModelScope.launch {
            val events = getEventsUseCase()

            _stateFlow.update { curentState ->
                curentState.copy(
                    events = events.map { ShortEventItem(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        cover = it.cover,
                        start = it.start,
                        end = it.end
                    ) }
                )
            }
        }
    }

    fun refreshData() {
        loadData()
    }

}
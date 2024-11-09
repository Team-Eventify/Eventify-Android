package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.repositories.events.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {
    var events by mutableStateOf(emptyList<EventInfo>())
        private set
    fun loadEvents(){
        viewModelScope.launch {
            val eventsResponse = eventsRepository.getEventsList()
            events = eventsResponse
        }
    }

}
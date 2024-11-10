package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.presentation.ui.navgraphs.HomeRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailViewmodel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val eventsRepository: EventsRepository
): ViewModel() {
    val eventDetail = savedStateHandle.toRoute<HomeRouter.EventDetail>()
    val eventId = eventDetail.eventId

    var event by mutableStateOf<EventInfo?>(null)

    fun loadEvent(){
        viewModelScope.launch {
            event = eventsRepository.getEventDetail(eventId)
        }
    }
}
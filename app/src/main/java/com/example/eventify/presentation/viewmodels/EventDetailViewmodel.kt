package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.EventInfo
import com.example.eventify.domain.usecases.events.GetEventDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventDetailUseCase: GetEventDetailUseCase
): ViewModel() {
//    private val eventId = savedStateHandle.toRoute<HomeRouter.EventDetail>().eventId
    private val eventId = ""

    var event by mutableStateOf<EventInfo?>(null)

    init {
        loadEvent()
    }

    fun loadEvent(){
        viewModelScope.launch {
            event = getEventDetailUseCase(eventId)
        }
    }
}
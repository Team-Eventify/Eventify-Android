package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.domain.di.MockedEventsRepository
import com.example.eventify.domain.usecases.GetCurrentUserUseCase
import com.example.eventify.presentation.models.ShortEventItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyEventsViewModel @Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val eventsRepository: EventsRepository
): ViewModel() {
    var upComingEvents by mutableStateOf<List<ShortEventItem>>(emptyList())
        private set

    var finishedEvents by mutableStateOf<List<ShortEventItem>>(emptyList())
        private set


    private suspend fun loadEvents(){
        upComingEvents = listOf(
            ShortEventItem(
                id = "",
                title = "День первокурсника",
                description = "",
                start = 1231313,
                end = 231231312
            ),
            ShortEventItem(
                id = "",
                title = "День открытых дверей",
                description = "",
                start = 2131313,
                end = 231231312
            ),
            ShortEventItem(
                id = "",
                title = "День хуйни",
                description = "",
                start = 1231313,
                end = 331231312
            )
        )

        finishedEvents = listOf(
            ShortEventItem(
                id = "",
                title = "День первокурсника",
                description = "",
                start = 1231313,
                end = 231231312
            ),
            ShortEventItem(
                id = "",
                title = "День открытых дверей",
                description = "",
                start = 2131313,
                end = 231231312
            ),
            ShortEventItem(
                id = "",
                title = "День хуйни",
                description = "",
                start = 1231313,
                end = 331231312
            )
        )
    }


    fun loadData(){
        viewModelScope.launch {
            loadEvents()
        }
    }


    init {
        loadData()
    }
}
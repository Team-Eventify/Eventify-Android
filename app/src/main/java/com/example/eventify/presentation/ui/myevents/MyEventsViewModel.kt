package com.example.eventify.presentation.ui.myevents

import androidx.lifecycle.ViewModel
import com.example.eventify.data.repositories.events.EventsRepository
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.presentation.models.ShortEventItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val eventsRepository: EventsRepository
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MyEventsState> = MutableStateFlow(MyEventsState.default())
    val stateFlow: StateFlow<MyEventsState> = _stateFlow.asStateFlow()

    init {
        loadData()
    }

    fun loadData(){
        _stateFlow.update { currentState ->
            currentState.copy(
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
                ),
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
            )
        }
    }

    fun refresh(){
        loadData()
    }

}
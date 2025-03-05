package com.example.eventify.presentation.ui.events.feedback

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val eventId = ""
    private val _stateFlow: MutableStateFlow<FeedbackState> = MutableStateFlow(FeedbackState())
    val stateFlow: StateFlow<FeedbackState> = _stateFlow.asStateFlow()

    fun navigateUp(){
        viewModelScope.launch {
//            navigator.navigateUp()
        }
    }

    fun changePositiveFeedbackText(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                positiveFeedbackText = value
            )
        }
    }

    fun changeNegativeFeedbackText(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                negativeFeedbackText = value
            )
        }
    }

    fun changeAdditionalFeedbackText(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                additionalFeedbackText = value
            )
        }
    }

    fun sendForm() {

    }


}
package com.example.eventify.presentation.ui.account.profile_decor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.presentation.ui.account.profile.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileDecorViewModel @Inject constructor(): ViewModel() {
    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )
}
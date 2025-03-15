package com.example.eventify.presentation.ui.account.profile_decor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.data.storages.StorageKeys
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorUiState
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileDecorViewModel @Inject constructor(
    @SharedStorage private val localeStorage: LocaleStorage
): ViewModel() {
    private val _stateFlow: MutableStateFlow<ProfileDecorUiState> = MutableStateFlow(ProfileDecorUiState.Loading)
    val stateFlow: StateFlow<ProfileDecorUiState> = _stateFlow
        .onStart { }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ProfileDecorUiState.Loading
        )

    fun changeTheme(typeOfTheme: TypesTheme) {
        when (typeOfTheme) {
            TypesTheme.SYSTEM_THEME -> {
                localeStorage.remove(StorageKeys.TYPE_THEME)
            }
            TypesTheme.LIGHT_THEME -> {
                localeStorage.put(StorageKeys.TYPE_THEME, TypesTheme.LIGHT_THEME)
            }
            TypesTheme.DARK_THEME -> {
                localeStorage.put(StorageKeys.TYPE_THEME, TypesTheme.DARK_THEME)
            }
        }
    }
}
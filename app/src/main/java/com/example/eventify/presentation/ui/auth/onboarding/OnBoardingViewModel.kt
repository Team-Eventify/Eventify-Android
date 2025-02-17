package com.example.eventify.presentation.ui.auth.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.data.storages.StorageKeys
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val navigator: Navigator,
    @SharedStorage private val localeStorage: LocaleStorage
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnBoardingState> = MutableStateFlow(OnBoardingState())
    val stateFlow: StateFlow<OnBoardingState> = _stateFlow.asStateFlow()

    fun finishOnboarding(){
        viewModelScope.launch {
            navigator.navigate(AuthRouter.RegisterRoute)
            localeStorage.put(StorageKeys.IS_SHOW_BOARDING, false)
        }
    }

}
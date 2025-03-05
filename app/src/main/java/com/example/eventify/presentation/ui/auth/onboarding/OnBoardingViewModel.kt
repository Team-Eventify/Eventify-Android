package com.example.eventify.presentation.ui.auth.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.data.storages.StorageKeys
import com.example.eventify.data.storages.StorageKeys.IS_SHOW_BOARDING
import com.example.eventify.presentation.ui.auth.onboarding.state.OnBoardingState
import com.example.eventify.presentation.ui.auth.onboarding.state.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

const val PAGES_AMOUNT = 5

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    @SharedStorage private val localeStorage: LocaleStorage
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnBoardingState> = MutableStateFlow(OnBoardingState())
    val stateFlow: StateFlow<OnBoardingState> = _stateFlow.asStateFlow()

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    fun nextPage() {
        if (stateFlow.value.currentPage + 1 >= PAGES_AMOUNT) {
            return finishOnboarding()
        }

        _stateFlow.update {
            it.copy(
                currentPage = it.currentPage + 1
            )
        }
    }

    fun goToPreviousPage() {
        if (0 < stateFlow.value.currentPage - 1)
        _stateFlow.update {
            it.copy(
                currentPage = it.currentPage - 1
            )
        }
    }

    fun goToPage(pageIndex: Int) {
        if (pageIndex !in 0 until PAGES_AMOUNT) return

        _stateFlow.update { it.copy(
            currentPage = pageIndex
        ) }
    }

    fun finishOnboarding() {
        viewModelScope.launch {
            localeStorage.put(IS_SHOW_BOARDING, false)
            mutableSideEffect.send(SideEffect.FinishOnBoarding)
        }
    }
}
package com.example.eventify.presentation.ui.account.profile

import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )

    fun loadData(){
        launchCatching(
            catch = { error ->
                _stateFlow.update {
                    UiState.Error(
                        message = error.message
                    )
                }
            }
        ) {
            _stateFlow.update {
                getCurrentUserUseCase().let { user ->
                    UiState.ShowProfile(
                        userInfo = UserShortInfo(
                            id = user.id,
                            firstName = user.firstName,
                            lastName = user.lastName,
                            email = user.email
                        )
                    )
                }

            }
        }
    }


    fun logOut(){
        launchCatching {
            updateAuthStateToUnauthorized()
        }
    }
}
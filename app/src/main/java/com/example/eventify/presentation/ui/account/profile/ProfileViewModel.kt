package com.example.eventify.presentation.ui.account.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.auth.LogOutUseCase
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.ui.account.profile.state.SideEffect
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.utils.asText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )

    fun loadData(){
        viewModelScope.launch {
            _stateFlow.update {
                when (val result = getCurrentUserUseCase()){
                    is Result.Error -> {
                        UiState.Error(result.asText(context))
                    }
                    is Result.Success -> {
                        UiState.ShowProfile(
                            userInfo = UserShortInfo(
                                id = result.data.id,
                                firstName = result.data.firstName,
                                lastName = result.data.lastName,
                                email = result.data.email
                            )
                        )
                    }
                }
            }
        }
    }


    fun logOut(){
        viewModelScope.launch {
            logOutUseCase()
            mutableSideEffect.send(SideEffect.LogOut)
        }
    }
}
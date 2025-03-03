package com.example.eventify.presentation.ui.account.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.DeleteAccountUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.auth.LogOutUseCase
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.navigation.navgraphs.SettingsRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {

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
                        SnackbarController.sendEvent(
                            SnackbarEvent(message = result.error.asUiText().asString(context))
                        )
                        UiState.Error
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
            navigator.navigate(AuthRouter.LogInRoute){
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }
}
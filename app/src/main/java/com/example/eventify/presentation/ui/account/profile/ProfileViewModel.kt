package com.example.eventify.presentation.ui.account.profile

import android.content.Context
import androidx.compose.material3.Snackbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.DeleteAccountUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.LogOutUseCase
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.navigation.navgraphs.SettingsRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState.default())
    val stateFlow: StateFlow<ProfileState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ProfileState.default()
        )

    fun loadData(){
        _stateFlow.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val currentUser = getCurrentUserUseCase()){
                is Result.Error -> SnackbarController.sendEvent(
                    SnackbarEvent(message = currentUser.error.asUiText().asString(context))
                )
                is Result.Success -> {
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            userInfo = UserShortInfo(
                                id = currentUser.data.id,
                                firstName = currentUser.data.firstName,
                                lastName = currentUser.data.lastName,
                                middleName = currentUser.data.middleName,
                                email = currentUser.data.email
                            )
                        )
                    }
                }
            }
        }
        _stateFlow.update { it.copy(isLoading = false) }
    }

    fun navigateToEditProfile() = viewModelScope.launch {
        navigator.navigate(SettingsRouter.ProfileEditRoute)
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

    fun deleteAccount(){
        viewModelScope.launch {
            deleteAccountUseCase()
            navigator.navigate(RootRouter.AuthRoute){
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateToAppInfo(){
        viewModelScope.launch {
            navigator.navigate(SettingsRouter.AboutAppRoute)
        }
    }

}
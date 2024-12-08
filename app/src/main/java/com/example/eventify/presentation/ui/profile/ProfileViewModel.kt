package com.example.eventify.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.usecases.account.DeleteAccountUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.LogOutUseCase
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.navigation.navgraphs.SettingsRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState.default())
    val stateFlow: StateFlow<ProfileState> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val currentUser = getCurrentUserUseCase()
            _stateFlow.update { currentState ->
                currentState.copy(
                    userInfo = UserShortInfo(
                        id = currentUser.id,
                        firstName = currentUser.firstName,
                        lastName = currentUser.lastName,
                        middleName = currentUser.middleName,
                        email = currentUser.email
                    )
                )
            }
        }
    }

    fun navigateToEditProfile() = viewModelScope.launch {
        navigator.navigate(SettingsRouter.ProfileEditRoute)
    }

    fun logOut(){
        viewModelScope.launch {
            logOutUseCase()
            navigator.navigate(AuthRouter.LogInRoute)
        }
    }

    fun deleteAccount(){
        viewModelScope.launch {
            deleteAccountUseCase()
            navigator.navigate(RootRouter.AuthRoute)
        }
    }

}
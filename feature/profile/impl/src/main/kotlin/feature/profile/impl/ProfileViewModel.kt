package feature.profile.impl

import androidx.lifecycle.viewModelScope
import core.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.account.GetCurrentUserUseCase
import feature.profile.impl.state.UiState
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import domain.models.UserShortInfo

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
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
                _stateFlow.update { UiState.Error }
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
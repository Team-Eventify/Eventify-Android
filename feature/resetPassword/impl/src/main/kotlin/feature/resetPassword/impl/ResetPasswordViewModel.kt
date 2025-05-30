package feature.resetPassword.impl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import core.common.navigation.ARG_SHARED_EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import feature.resetPassword.impl.state.UiState
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val sharedEmail by lazy {
        savedStateHandle.get<String?>(ARG_SHARED_EMAIL) ?: ""
    }
    private val _stateFlow: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(
            email = sharedEmail
        ))
    val stateFlow: StateFlow<UiState> = _stateFlow.asStateFlow()

    fun onChangeEmail(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                email = value
            )
        }
    }

    fun resetPassword(){

    }
}
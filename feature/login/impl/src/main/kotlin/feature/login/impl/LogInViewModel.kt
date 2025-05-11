package feature.login.impl

import core.common.BaseViewModel
import core.common.extentions.isNotFound
import dagger.hilt.android.lifecycle.HiltViewModel
import data.models.UserCredentials
import domain.auth.LoginUseCase
import feature.login.impl.state.LogInState
import feature.login.impl.state.SideEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
internal class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<LogInState> = MutableStateFlow(LogInState())
    val stateFlow: StateFlow<LogInState> = _stateFlow.asStateFlow()

    fun changeLogin(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    fun changePassword(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    fun logIn(login: String, password: String) {
        launchCatching(
            catch = ::handleErrors
        ) {
            val userCredentials = UserCredentials(
                login = login,
                password = password
            )

            loginUseCase(credentials = userCredentials)
            mutableSideEffect.send(SideEffect.SuccessLogIn)
        }

    }

    private fun handleErrors(exception: Throwable): Unit {
        _stateFlow.update { currentState ->
            currentState.copy(
                hasLoginError = true,
                hasPasswordError = true,
                loginError = null,
                passwordError = null,
            )
        }
        when {
            exception.isNotFound() -> mutableSideEffect.trySend(SideEffect.UnsuccessLogIn)
            else -> mutableSideEffect.trySend(SideEffect.ServerError)
        }
    }

    override fun updateAuthStateToUnauthorized() {
        mutableSideEffect.trySend(
            SideEffect.UnsuccessLogIn
        )
        _stateFlow.update { currentState ->
            currentState.copy(
                hasLoginError = true,
                hasPasswordError = true,
                loginError = null,
                passwordError = null,
            )
        }
    }

    private fun validateFormData(): Boolean{
        return _stateFlow.value.run { isValidLogin && isValidPassword  }
    }
}
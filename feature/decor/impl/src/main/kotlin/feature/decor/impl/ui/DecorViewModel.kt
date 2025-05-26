package feature.decor.impl.ui

import core.common.BaseViewModel
import feature.decor.impl.state.SideEffect
import core.common.theme.AppThemeManager
import core.common.theme.ThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import feature.decor.impl.state.DecorUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
internal class DecorViewModel @Inject constructor(
    private val appThemeManager: AppThemeManager
): BaseViewModel() {

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val mutableState = MutableStateFlow(DecorUiState())
    val state = mutableState.asStateFlow()

    init {
        launchCatching {
            mutableState.update {
                DecorUiState(
                    currentTheme = appThemeManager.getCurrentTheme()
                )
            }

        }
    }

    fun changeTheme(theme: ThemeType) {
        appThemeManager.changeTheme(theme)
        mutableState.update { currentState ->
            currentState.copy(
                currentTheme = theme
            )
        }
    }
}
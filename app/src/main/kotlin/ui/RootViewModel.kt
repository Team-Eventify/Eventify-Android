package ui

import core.common.BaseViewModel
import core.common.theme.AppThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.auth.LogOutUseCase
import javax.inject.Inject


@HiltViewModel
class RootViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    val themeManager: AppThemeManager
) : BaseViewModel() {
    val authState by lazy { authProvider.authorizationState }

    fun logOut() {
        launchCatching {
            logOutUseCase()
        }
    }
}
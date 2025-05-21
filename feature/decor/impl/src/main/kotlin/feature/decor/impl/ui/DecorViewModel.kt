package feature.decor.impl.ui

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.decor.impl.state.SideEffect
import core.common.storages.LocaleStorage
import core.common.storages.SharedStorage
import core.common.theme.AppThemeManager
import core.common.theme.ThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import domain.models.LogoIcon
import feature.decor.impl.state.DecorUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DecorViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @SharedStorage private val localeStorage: LocaleStorage,
    private val appThemeManager: AppThemeManager
): ViewModel() {

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val mutableState = MutableStateFlow(DecorUiState())
    val state = mutableState.asStateFlow()


    fun getPreviousAlias() = localeStorage.getString("alias_icon", null)

    fun updateIcon(icon: LogoIcon) {
        val prevAlias = getPreviousAlias()

        if (!prevAlias.equals(icon.alias)) {
            viewModelScope.launch {
                try {
                    var result = "${context.packageName}.ui.MainActivity"
                    if (prevAlias != null) result = "${context.packageName}${prevAlias}"

                    context.packageManager.setComponentEnabledSetting(
                        ComponentName(
                            context,
                            result
                        ),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP
                    )

                    context.packageManager.setComponentEnabledSetting(
                        ComponentName(context, "${context.packageName}${icon.alias}"),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP
                    )

                    localeStorage.put("alias_icon", icon.alias)

                    mutableSideEffect.send(SideEffect.SuccessUpdate)
                } catch (e: Exception) {
                    mutableSideEffect.send(SideEffect.FailUpdate(e.localizedMessage))
                }
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
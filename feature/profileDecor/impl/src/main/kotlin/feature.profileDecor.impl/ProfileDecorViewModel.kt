package feature.profileDecor.impl

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.presentation.ui.account.profile_decor.state.SideEffect
import core.common.storages.LocaleStorage
import core.common.storages.SharedStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import domain.models.LogoIcon
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDecorViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @SharedStorage private val localeStorage: LocaleStorage
): ViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()


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
}
package com.example.eventify.presentation.ui.account.profile_decor

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.presentation.models.LogoIcon
import com.example.eventify.presentation.ui.account.profile_decor.state.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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


    fun updateIcon(icon: LogoIcon) {
        val prevAlias = localeStorage.getString("alias_icon", null)

        if (!prevAlias.equals(icon.alias)) {
            context.packageManager.setComponentEnabledSetting(
                ComponentName(context, icon.alias),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            context.packageManager.setComponentEnabledSetting(
                ComponentName(context, prevAlias ?: "com.example.eventify.presentation.MainActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            localeStorage.put("alias_icon", icon.alias)
            viewModelScope.launch {
                mutableSideEffect.send(SideEffect.SuccessUpdate)
            }
        }
    }
}
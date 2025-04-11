package com.example.eventify.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.StorageKeys
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme

class AppThemeManager(
    val localeStorage: LocaleStorage,
) {
    var isDarkTheme: MutableState<Boolean?> = mutableStateOf(null)

    init {
        val num: Int = localeStorage.getInt(StorageKeys.TYPE_THEME, -1)
        when (num) {
            -1 -> isDarkTheme.value = null
            0 -> isDarkTheme.value = true
            1 -> isDarkTheme.value = false
        }
    }


    fun changeTheme(typeOfTheme: TypesTheme) {
        when (typeOfTheme) {
            TypesTheme.SYSTEM_THEME -> {
                localeStorage.remove(StorageKeys.TYPE_THEME)
                isDarkTheme.value = null
            }
            TypesTheme.LIGHT_THEME -> {
                localeStorage.put(StorageKeys.TYPE_THEME, TypesTheme.LIGHT_THEME.ordinal)
                isDarkTheme.value = false
            }
            TypesTheme.DARK_THEME -> {
                localeStorage.put(StorageKeys.TYPE_THEME, TypesTheme.DARK_THEME.ordinal)
                isDarkTheme.value = true
            }
        }
    }

    fun getTypeTheme(): TypesTheme {
        if (isDarkTheme.value == null) return TypesTheme.SYSTEM_THEME
        else if (isDarkTheme.value == true) return TypesTheme.DARK_THEME
        return TypesTheme.LIGHT_THEME
    }
}

@Composable
fun rememberAppTheme(localeStorage: LocaleStorage): AppThemeManager {
    return remember {
        AppThemeManager(localeStorage)
    }
}

val LocalAppThemeState = staticCompositionLocalOf<AppThemeManager> {
    error("AppThemeManager not provided")
}
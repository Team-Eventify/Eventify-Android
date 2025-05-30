package core.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import core.common.storages.LocaleStorage
import core.common.storages.SharedStorage
import core.common.storages.StorageKeys
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppThemeManager @Inject constructor(
    @SharedStorage val localeStorage: LocaleStorage
) {
    val isDarkTheme: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    init {
        val darkThemeEnabled = localeStorage.getString(StorageKeys.IS_DARK_THEME, null)?.toBooleanStrictOrNull()
        isDarkTheme.update { darkThemeEnabled }
    }


    fun changeTheme(typeOfTheme: ThemeType) {
        localeStorage.put(StorageKeys.IS_DARK_THEME, typeOfTheme.isDarkTheme.toString())
        isDarkTheme.update { typeOfTheme.isDarkTheme }
    }

//    fun getTypeTheme(): ThemeType {
//        if (isDarkTheme.value == null) return TypesTheme.SYSTEM_THEME
//        else if (isDarkTheme.value == true) return TypesTheme.DARK_THEME
//        return TypesTheme.LIGHT_THEME
//    }
}

@Composable
fun rememberAppTheme(localeStorage: LocaleStorage): AppThemeManager {
    return remember {
        AppThemeManager(localeStorage)
    }
}


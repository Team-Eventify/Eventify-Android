package core.common.theme

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

    fun  getCurrentTheme(): ThemeType {
        return when (localeStorage.getString(StorageKeys.IS_DARK_THEME, null)?.toBooleanStrictOrNull()) {
            true -> ThemeType.DARK
            false -> ThemeType.LIGHT
            null -> ThemeType.SYSTEM
        }
    }



    fun changeTheme(typeOfTheme: ThemeType) {
        localeStorage.put(StorageKeys.IS_DARK_THEME, typeOfTheme.isDarkTheme.toString())
        isDarkTheme.update { typeOfTheme.isDarkTheme }
    }
}


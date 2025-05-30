package core.common.theme

enum class ThemeType(val isDarkTheme: Boolean?) {
    SYSTEM(null),
    LIGHT(false),
    DARK(true),
}
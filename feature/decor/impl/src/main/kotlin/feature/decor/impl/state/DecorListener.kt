package feature.decor.impl.state

import core.common.theme.ThemeType

interface DecorListener {
    fun onChangeTheme(theme: ThemeType)
    fun navigateBack()
}
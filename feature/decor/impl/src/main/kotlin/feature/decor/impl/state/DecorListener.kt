package feature.decor.impl.state

import domain.models.LogoIcon

interface DecorListener {
    fun onBackClick(): Unit
    fun changeTheme(typeOfTheme: TypesTheme): Unit
    fun getActiveTheme(): TypesTheme
    fun changeIcon(icon: LogoIcon): Unit
}
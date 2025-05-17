package com.example.eventify.presentation.ui.account.profile_decor.state

import domain.models.LogoIcon

interface ProfileDecorRouteListener {
    fun onBackClick(): Unit
    fun changeTheme(typeOfTheme: TypesTheme): Unit
    fun getActiveTheme(): TypesTheme
    fun changeIcon(icon: LogoIcon): Unit
}
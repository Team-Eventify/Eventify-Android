package com.example.eventify.presentation.ui.account.profile_decor.state

import com.example.eventify.presentation.models.LogoIcon

interface ProfileDecorRouteListener {
    fun onBackClick(): Unit
    fun changeTheme(typeOfTheme: TypesTheme): Unit
    fun getActiveTheme(): TypesTheme
    fun changeIcon(icon: LogoIcon): Unit
}
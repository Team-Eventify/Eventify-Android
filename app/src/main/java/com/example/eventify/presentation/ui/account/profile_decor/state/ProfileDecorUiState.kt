package com.example.eventify.presentation.ui.account.profile_decor.state

import com.example.eventify.R
import com.example.eventify.presentation.models.LogoIcon

data class ProfileDecorUiState(
    val listIcons: List<LogoIcon> = listOf<LogoIcon>(LogoIcon("Основная", R.drawable.icon_logo_1, "com.example.eventify.alias.icon_logo_1"),
        LogoIcon("Основная", R.drawable.icon_logo_3, "com.example.eventify.alias.icon_logo_3"),
        LogoIcon("Основная", R.drawable.icon_logo_4, "com.example.eventify.alias.icon_logo_4"),
        LogoIcon("Основная", R.drawable.icon_logo_5, "com.example.eventify.alias.icon_logo_5")),
    val isSystemOrDarkTheme: Boolean = true,
    val activeTypeOfTheme: Boolean? = null

)
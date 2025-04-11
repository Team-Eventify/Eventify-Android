package com.example.eventify.presentation.ui.account.profile_decor.state

import com.example.eventify.R
import com.example.eventify.presentation.models.LogoIcon

data class ProfileDecorUiState(
    val listIcons: List<LogoIcon> = listOf<LogoIcon>(LogoIcon("Основная", R.drawable.icon_logo_1, "com.example.eventify.alias.iconLogo1"),
        LogoIcon("Основная", R.drawable.icon_logo_3, "com.example.eventify.alias.iconLogo3"),
        LogoIcon("Основная", R.drawable.icon_logo_4, "com.example.eventify.alias.iconLogo4"),
        LogoIcon("Основная", R.drawable.icon_logo_5, "com.example.eventify.alias.iconLogo5"))
)
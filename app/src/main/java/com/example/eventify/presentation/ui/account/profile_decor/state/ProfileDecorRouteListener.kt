package com.example.eventify.presentation.ui.account.profile_decor.state

interface ProfileDecorRouteListener {
    fun onBackClick(): Unit
    fun changeTheme(typeOfTheme: TypesTheme): Unit
}
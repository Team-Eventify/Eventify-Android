package com.example.eventify.presentation.utils.auth

import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath

sealed class AuthorizeType(val route: String){
    data object Unauthorized : AuthorizeType(AuthRootPath.baseRoute)
    data object Authorized : AuthorizeType(EventsRootPath.baseRoute)
}


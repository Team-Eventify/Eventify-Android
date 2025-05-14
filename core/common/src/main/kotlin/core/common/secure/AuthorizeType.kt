package core.common.secure

import core.common.navigation.AuthRoot
import core.common.navigation.EventsRoot

sealed class AuthorizeType(val route: String){
    data object Unauthorized : AuthorizeType(AuthRoot.baseRoute)
    data object Authorized : AuthorizeType(EventsRoot.baseRoute)
}
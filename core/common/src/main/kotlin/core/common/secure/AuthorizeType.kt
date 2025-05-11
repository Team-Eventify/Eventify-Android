package core.common.secure

sealed class AuthorizeType(val route: String){
    data object Unauthorized : AuthorizeType(AuthRootPath.baseRoute)
    data object Authorized : AuthorizeType(EventsRootPath.baseRoute)
}
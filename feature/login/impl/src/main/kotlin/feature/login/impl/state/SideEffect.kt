package feature.login.impl.state

sealed class SideEffect {
    data object SuccessLogIn : SideEffect()
    data object ServerError : SideEffect()
    data object UnsuccessLogIn : SideEffect()
}
package feature.register.impl.state


sealed class SideEffect {
    data object SuccessRegister : SideEffect()
    data object FailedRegister : SideEffect()
    data object FailedSendOTP : SideEffect()
    data object ServerError : SideEffect()
}
package feature.register.impl.state


sealed class SideEffect {
    data object SuccessRegister : SideEffect()
    data class FailRegister(val message: String? = null) : SideEffect()
    data object ServerError : SideEffect()
}
package feature.setup.impl.state

sealed class SideEffect {
    data object FinishSetUp : SideEffect()
    data object FailedSetUserData : SideEffect()
    data class FailedProvideCategories(val message: String? = null) : SideEffect()
}
package feature.setup.impl.state

sealed class SideEffect {
    data object FinishSetUp : SideEffect()
    data class FailedProvideCategories(val message: String? = null) : SideEffect()
}
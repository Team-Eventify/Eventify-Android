package feature.eventDetail.impl.state

internal sealed class SideEffect {
    data object SuccessSubscribeEvent : SideEffect()
    data object SuccessUnsubscribeEvent : SideEffect()

    data class FailSubscribeEvent(val message: String? = null) : SideEffect()
    data class FailUnsubscribeEvent(val message: String? = null) : SideEffect()
}
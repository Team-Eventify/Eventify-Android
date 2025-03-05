package com.example.eventify.presentation.ui.events.eventdetail.state

sealed class SideEffect {
    data object SuccessSubscribeEvent : SideEffect()
    data object SuccessUnsubscribeEvent : SideEffect()

    data class FailSubscribeEvent(val message: String? = null) : SideEffect()
    data class FailUnsubscribeEvent(val message: String? = null) : SideEffect()
}
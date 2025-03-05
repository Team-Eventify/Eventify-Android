package com.example.eventify.presentation.ui.auth.choosecategories.state

sealed class SideEffect {
    data object FinishSetUp : SideEffect()
    data class FailedProvideCategories(val message: String? = null) : SideEffect()
}
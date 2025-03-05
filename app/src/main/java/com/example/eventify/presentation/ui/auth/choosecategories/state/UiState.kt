package com.example.eventify.presentation.ui.auth.choosecategories.state

import com.example.eventify.presentation.models.CategorySelectItem

data class SetUpState(
    val categoryItems: List<CategorySelectItem> = emptyList()
) {
    val isValidData: Boolean
        get() = categoryItems.any { it.selected }
}
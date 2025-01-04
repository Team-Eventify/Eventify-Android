package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.runtime.Stable
import com.example.eventify.presentation.models.CategorySelectItem


/**
 * UI State that represents ChooseCategoriesScreen
 **/
@Stable
data class ChooseCategoriesState(
    val categoryItems: List<CategorySelectItem> = emptyList()
){
    val isValidData: Boolean
        get() = categoryItems.any { it.selected }
}

/**
 * ChooseCategories Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
@Stable
data class ChooseCategoriesActions(
    val onSkip: () -> Unit = {},
    val onSubmit: () -> Unit = {},
    val onChangeCategoryFilterActive: (String, Boolean) -> Unit = {_, _ ->}
)
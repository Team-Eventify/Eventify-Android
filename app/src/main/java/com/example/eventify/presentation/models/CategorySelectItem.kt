package com.example.eventify.presentation.models

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class CategorySelectItem(
    val id: String,
    val title: String,
    val selected: Boolean = false,
    val isShow: Boolean = true,
    val color: Color,
)

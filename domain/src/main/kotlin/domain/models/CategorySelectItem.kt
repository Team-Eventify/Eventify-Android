package domain.models

import androidx.compose.ui.graphics.Color

data class CategorySelectItem(
    val id: String,
    val title: String,
    val selected: Boolean = false,
    val isShow: Boolean = true,
    val color: Color,
)
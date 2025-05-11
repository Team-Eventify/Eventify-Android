package domain.models

import android.graphics.Color


data class CategorySelectItem(
    val id: String,
    val title: String,
    val selected: Boolean = false,
    val isShow: Boolean = true,
    val color: Color,
)
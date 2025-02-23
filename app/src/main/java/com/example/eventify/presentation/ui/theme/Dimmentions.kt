package com.example.eventify.presentation.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val space10 = 10.dp
val space12 = 12.dp
val space14 = 14.dp
val space16 = 16.dp
val space18 = 18.dp
val space20 = 20.dp
val space22 = 22.dp
val space24 = 24.dp


data class Dimentions(
    val windowPaddings: PaddingValues = PaddingValues(space16)
)

val LocalDimentions = staticCompositionLocalOf<Dimentions> { Dimentions() }

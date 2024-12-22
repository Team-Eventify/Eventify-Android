package com.example.eventify.presentation.models

import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier


@Stable
data class ScaffoldViewState(
    val modifier: Modifier = Modifier,
    val topBar: @Composable () -> Unit = {},
    val showBottomBar: Boolean = true,
    val floatingActionButton: @Composable () -> Unit = {},
    val floatingActionButtonPosition: FabPosition = FabPosition.End,
){
    companion object {
        fun default() = ScaffoldViewState()
    }
}

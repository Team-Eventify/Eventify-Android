package com.example.eventify.presentation.ui.common.topBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState


@Composable
fun EventifyTopAppBar(
    state: TopBarState,
    modifier: Modifier = Modifier
) {
    when (state) {
        TopBarState.None -> {}
        is TopBarState.Base -> {
            when (state.size) {
                TopBarSize.SMALL, TopBarSize.UNKNOWN -> {
                    SmallTopBar(state, modifier)
                }
                TopBarSize.MEDIUM -> {
                    MediumTopBar(state, modifier)
                }
                TopBarSize.LARGE -> TODO()
            }
        }
        is TopBarState.Search -> {
            SearchTopBar()
        }
    }

}
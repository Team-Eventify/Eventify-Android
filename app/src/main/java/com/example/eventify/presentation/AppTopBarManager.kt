package com.example.eventify.presentation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

enum class TopBarSize {
    SMALL,
    MEDIUM,
    LARGE,
    UNKNOWN,
}

data class TopBarAction(
    @DrawableRes val iconRes: Int,
    val onClick: () -> Unit
)

sealed class TopBarState {
    data object None : TopBarState()

    @Stable
    data class Base(
        val title: String,
        val size: TopBarSize,
        val subtitle: String? = null,
        val leftAction: TopBarAction? = null,
        val rightActions: List<TopBarAction> = emptyList(),
        val colapseRightActions: Boolean = false,
        val containerColor: Color? = null,
    ) : TopBarState()

    data class Search(
        val searchText: String
    ) : TopBarState()
}



class AppTopBarManager(
    initialTopBarState: TopBarState = TopBarState.None,
    initialIsVisible: Boolean = false,
) {
    var isVisible by mutableStateOf(initialIsVisible)
        private set

    var topBarState by mutableStateOf<TopBarState>(initialTopBarState)
        private set

    fun setUp(topBarType: TopBarState): AppTopBarManager {
        isVisible = true
        topBarState = topBarType
        return this
    }

    fun hide() {
        isVisible = false
    }
}

@Composable
fun rememberTopBarState(): AppTopBarManager {
    return remember {
        AppTopBarManager()
    }
}


val LocalTopBarState = staticCompositionLocalOf<AppTopBarManager> {
    error("AppTopBarState not provided")
}

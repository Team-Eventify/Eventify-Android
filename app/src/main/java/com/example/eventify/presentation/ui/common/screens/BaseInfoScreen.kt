package com.example.eventify.presentation.ui.common.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.ui.common.AnnotationText
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BaseInfoScreen(
    icon: @Composable() (ColumnScope.() -> Unit),
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    button: (@Composable() (ColumnScope.() -> Unit))? = null,
    verticalArrangement: Arrangement. Vertical = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
    horizontalAlignment: Alignment. Horizontal = Alignment.CenterHorizontally,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
    ) {
        icon()

        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        description?.let {
            AnnotationText(
                text = it,
                textAlign = TextAlign.Center
            )
        }

        button?.invoke(this)
    }
}


@Composable
fun RefreshableInfoScreen(
    icon: @Composable() (ColumnScope.() -> Unit),
    title: String,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    button: (@Composable() (ColumnScope.() -> Unit))? = null,
    verticalArrangement: Arrangement. Vertical = Arrangement.Center,
    horizontalAlignment: Alignment. Horizontal = Alignment.CenterHorizontally,
    swipeEnabled: Boolean = true,
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
        swipeEnabled = swipeEnabled,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BaseInfoScreen(
            icon = icon,
            title = title,
            modifier = modifier,
            description = description,
            button = button,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        )
    }
}


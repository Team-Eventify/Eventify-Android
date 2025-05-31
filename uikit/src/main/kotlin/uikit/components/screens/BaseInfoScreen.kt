package uikit.components.screens

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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import uikit.TypographyKit
import uikit.space10
import uikit.utils.conditional

@Composable
fun BaseInfoScreen(
    icon: @Composable() (ColumnScope.() -> Unit),
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    button: (@Composable() (ColumnScope.() -> Unit))? = null,
    verticalArrangement: Arrangement. Vertical = Arrangement.spacedBy(space10, Alignment.CenterVertically),
    horizontalAlignment: Alignment. Horizontal = Alignment.CenterHorizontally,
    fillMaxSize: Boolean = true,
) {
    Column(
        modifier = Modifier
            .conditional(fillMaxSize){
                fillMaxSize()
            }
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
            Text(
                text = it,
                textAlign = TextAlign.Center,
                style = TypographyKit.Body.regular,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
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


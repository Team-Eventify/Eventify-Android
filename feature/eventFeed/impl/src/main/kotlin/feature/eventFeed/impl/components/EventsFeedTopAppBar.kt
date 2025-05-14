package feature.eventFeed.impl.components

import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.uikit.R as UiKitR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EventsFeedTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors =  TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        modifier = modifier,
        title = {
            Text(stringResource(UiKitR.string.events_feed_title))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "EventsFeedTopAppBar")
@Composable
private fun PreviewEventsFeedTopAppBar() {
    EventsFeedTopAppBar()
}
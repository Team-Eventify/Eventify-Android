package com.example.eventify.presentation.ui.events.eventsfeed

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.common.HeadingText
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.UUID
import kotlin.random.Random

@Composable
fun EventsFeedScreen(
    state: UiState.ShowFeed,
    actions: EventsFeedActions,
    imageLoader: ImageLoader
) {
    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = actions.onRefreshData
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 15.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HeadingText(stringResource(R.string.popular_events))
            }
            items(
                items = state.events,
                key = { it.id }
            ){ event ->
                EventCard(
                    event = event,
                    onClick = actions.onEventClick,
                    imageLoader = imageLoader,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EventsFeedScreenDefaultDarkPreview() {
    EventifyTheme {
        Surface {
            EventsFeedScreen(
                state = UiState.ShowFeed(
                    events = List(3){
                        ShortEventItem(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(3)
                                .values
                                .toList()
                                .joinToString { it },
                            description = LoremIpsum(20)
                                .values
                                .toList()
                                .joinToString { it },
                            cover = "",
                            start = Random.nextLong(),
                            end = Random.nextLong(),
                            location = LoremIpsum(15)
                                .values
                                .joinToString(),
                        )
                    },
                ),
                actions = EventsFeedActions.default(),
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}

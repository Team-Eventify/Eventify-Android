package com.example.eventify.presentation.ui.events.eventsfeed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.ui.theme.EventifyTheme
import kotlin.random.Random

@Composable
fun EventsFeedLazyColumn(
    events: List<ShortEventItem>,
    eventItem:  @Composable (ShortEventItem) -> Unit,
    loadingEventItem: @Composable () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    loadingEventCount: Int = 6,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        modifier = Modifier
            .fillMaxSize()
            .composed { modifier }
    ){
        item {
            HeadingText(stringResource(R.string.popular_events))
        }
        if (isLoading){
            items(loadingEventCount){
                loadingEventItem()
            }
        }else{
            items(
                items = events,
                key = { it.id }
            ){ event ->
                eventItem(event)
                HorizontalDivider()
            }
        }
    }
}

@Preview(name = "EventsFeedLazyColumn")
@Composable
private fun PreviewEventsFeedLazyColumnDark() {
    EventifyTheme(darkTheme = true) {
        Surface{
            EventsFeedLazyColumn(
                events = List(5){
                    ShortEventItem(
                        id = "",
                        title = LoremIpsum(2)
                            .values
                            .toList()
                            .joinToString { it },
                        description = LoremIpsum(9)
                            .values
                            .toList()
                            .joinToString { it },
                        cover = "",
                        start = Random.nextInt(),
                        end = Random.nextInt()
                    )
                },
                loadingEventItem = {
                    LoadingEventCard()
                },
                eventItem = { event ->
                    EventCard(
                        event = event,
                        onClick = {},
                        imageLoader = ImageLoader(LocalContext.current)
                    )
                },
                isLoading = false
            )
        }
    }
}
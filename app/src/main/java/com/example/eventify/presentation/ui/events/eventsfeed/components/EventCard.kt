package com.example.eventify.presentation.ui.events.eventsfeed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.EventCardTitle
import com.example.eventify.presentation.ui.shared.EventInfoTag
import com.example.eventify.presentation.ui.shared.shimmer
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.asDate
import com.example.eventify.presentation.utils.asTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventCard(
    event: ShortEventItem,
    onClick: (String) -> Unit,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onClick(event.id)
                }
        ) {
            var showShimmer by remember { mutableStateOf(false) }

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://eventify.website/api/v1/files/${event.cover}")
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.underfind_event_image),
                placeholder = painterResource(R.drawable.underfind_event_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                imageLoader = imageLoader,
                onLoading = {
                    showShimmer = true
                },
                onError = {
                    showShimmer = false
                },
                onSuccess = {
                    showShimmer = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer(
                        showShimmer = showShimmer,
                        blendMode = BlendMode.SrcAtop
                    )
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    EventInfoTag(event.start.asDate())
                    EventInfoTag(event.start.asTime())
                    EventInfoTag(event.location)
                }
                EventCardTitle(
                    text = event.title,
                    textColor = MaterialTheme.colorScheme.onSurface
                )
                BodyText(event.description, maxlines = 7)
            }
        }
    }
}

@Preview(name = "EventCardDark")
@Composable
private fun PreviewEventCardDark() {
    EventifyTheme(darkTheme = true) {
        Surface {
            EventCard(
                event = ShortEventItem(
                    id = "",
                    title = LoremIpsum(3)
                        .values
                        .joinToString(),
                    description = LoremIpsum(50)
                        .values
                        .joinToString(),
                    start = 1324412412,
                    end = 1324419999,
                    location = LoremIpsum(15)
                        .values
                        .joinToString(),
                ),
                onClick = {},
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}

@Preview(name = "EventCardLight")
@Composable
private fun PreviewEventCardLight() {
    EventifyTheme(darkTheme = false) {
        Surface {
            EventCard(
                event = ShortEventItem(
                    id = "",
                    title = LoremIpsum(3)
                        .values
                        .joinToString(),
                    description = LoremIpsum(50)
                        .values
                        .joinToString(),
                    start = 1324412412,
                    end = 1324419999,
                    location = LoremIpsum(2)
                        .values
                        .joinToString(),
                ),
                onClick = {},
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}
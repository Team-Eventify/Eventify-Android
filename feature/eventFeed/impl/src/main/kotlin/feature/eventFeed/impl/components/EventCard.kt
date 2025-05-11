package feature.eventFeed.impl.components

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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uikit.EventifyTheme
import uikit.LocaleImageLoader
import uikit.components.BodyText
import uikit.components.EventCardTitle
import uikit.components.EventInfoTag
import uikit.components.shimmer


@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun EventCard(
    event: ShortEventItem,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val localeImageLoader = LocaleImageLoader.current

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
                imageLoader = localeImageLoader,
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
                    EventInfoTag(event.duration)
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
                    state = EventState.UNKNOWN,
                    location = LoremIpsum(15)
                        .values
                        .joinToString(),
                ),
                onClick = {},
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
                    state = EventState.UNKNOWN,
                    end = 1324419999,
                    location = LoremIpsum(2)
                        .values
                        .joinToString(),
                ),
                onClick = {},
            )
        }
    }
}
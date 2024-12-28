package com.example.eventify.presentation.ui.events.eventsfeed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp

import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.models.date
import com.example.eventify.presentation.models.time
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.EventCardTitle
import com.example.eventify.presentation.ui.shared.EventInfoChip
import com.example.eventify.presentation.ui.shared.shimmer
import com.example.eventify.presentation.ui.theme.EventifyTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventCard(
    event: ShortEventItem,
    onClick: (String) -> Unit,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier
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
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            EventInfoChip(event.localDateTimeStart.date(short = true))
            EventInfoChip(event.localDateTimeStart.time(short = true))
            EventInfoChip("онлайн")
        }
        EventCardTitle(
            text = event.title,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        BodyText(event.description, maxlines = 7)
        Spacer(modifier = Modifier.height(10.dp))

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
                    title = "День открытых дверей",
                    description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.",
                    start = 1324412412,
                    end = 1324419999
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
                    title = "День открытых дверей",
                    description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.",
                    start = 1324412412,
                    end = 1324419999
                ),
                onClick = {},
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}
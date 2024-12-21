package com.example.eventify.presentation.ui.eventsfeed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.models.date
import com.example.eventify.presentation.models.time
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.EventCardTitle
import com.example.eventify.presentation.ui.shared.EventInfoChip

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

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://eventify.website/api/v1/files/${event.cover}")
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.underfind_event_image),
            placeholder = painterResource(R.drawable.underfind_event_image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            imageLoader = imageLoader,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
        EventCardTitle(
            text = event.title,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        BodyText(event.description, maxlines = 7)
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            EventInfoChip(event.localDateTimeStart.date(short = true))
            EventInfoChip(event.localDateTimeStart.time(short = true))
            EventInfoChip("онлайн")
        }
    }
}

//@Preview(name = "EventCard", showSystemUi = true, showBackground = true)
//@Composable
//private fun PreviewEventCard() {
//    EventCard(
//        event = ShortEventItem(
//            id = "",
//            title = "",
//            description = "",
//            start = 1324412412,
//            end = 1324419999
//        ),
//        onClick = {}
//    )
//}
package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.models.date
import com.example.eventify.presentation.models.time

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventCard(
    event: ShortEventItem,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painterResource(R.drawable.default_event_image),
            contentDescription = "Nothing",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
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

@Preview(name = "EventCard", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewEventCard() {
    EventCard(
        event = ShortEventItem(
            id = "",
            title = "",
            description = "",
            start = 1324412412,
            end = 1324419999
        ),
        onClick = {}
    )
}
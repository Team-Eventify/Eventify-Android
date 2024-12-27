package com.example.eventify.presentation.ui.myevents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.models.date
import com.example.eventify.presentation.models.time
import com.example.eventify.presentation.ui.shared.EventCardTitle
import com.example.eventify.presentation.ui.shared.UpComingEventInfoChip
import com.example.eventify.presentation.ui.theme.EventifyTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UpComingEventCard(
    event: ShortEventItem,
    modifier: Modifier = Modifier,
    onClick: ((String) -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF242427)
        ),
        onClick = { onClick?.invoke(event.id) },
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Ensures height matches content
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Left Column (Event Info)
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)// Ensure it fills the container's height
                    .weight(.6f) // Let this column take 55% of the space
            ) {
                EventCardTitle(
                    text = event.title,
                    textColor = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
                ) {
                    UpComingEventInfoChip(text = event.localDateTimeStart.date())
                    UpComingEventInfoChip(text = event.localDateTimeStart.time())
                    UpComingEventInfoChip(text = "онлайн")
                }

            }

            // Right Image
            Image(
                painter = painterResource(R.drawable.eventify_group_1),
                contentDescription = null,
                contentScale = ContentScale.FillHeight, // Adjust to fill the container's height
                modifier = Modifier
                    .fillMaxHeight() // Ensure the image takes up full height
                    .weight(0.4f) // Let the image take 45% of the space
            )
        }
    }
}

@Preview(name = "UpComingEventCard")
@Composable
private fun PreviewMyEventCardDark() {
    EventifyTheme(darkTheme = true) {
        UpComingEventCard(
            event = ShortEventItem(
                id = "",
                title = LoremIpsum(3)
                    .values
                    .toList()
                    .joinToString { it },
                description = LoremIpsum(9)
                    .values
                    .toList()
                    .joinToString { it },
                start = 1231313,
                end = 231231312
            )
        )
    }
}

@Preview(name = "UpComingEventCard")
@Composable
private fun PreviewMyEventCardLight() {
    EventifyTheme {
        UpComingEventCard(
            event = ShortEventItem(
                id = "",
                title = LoremIpsum(3)
                    .values
                    .toList()
                    .joinToString { it },
                description = "",
                start = 1231313,
                end = 231231312
            )
        )
    }
}
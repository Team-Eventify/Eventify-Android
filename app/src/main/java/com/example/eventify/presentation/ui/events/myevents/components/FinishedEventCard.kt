package com.example.eventify.presentation.ui.events.myevents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.shared.EventCardTitle
import com.example.eventify.presentation.ui.shared.FinishedEventInfoChip
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.asDate
import com.example.eventify.presentation.utils.asTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FinishedEventCard(
    event: ShortEventItem,
    modifier: Modifier = Modifier,
    onClick: ((String) -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFC7C7C7)
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
                    textColor = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
                ) {
                    FinishedEventInfoChip(text = event.start.asDate())
                    FinishedEventInfoChip(text = event.start.asTime())
                    FinishedEventInfoChip(text = "онлайн")
                }

            }

            // Right Image
            Image(
                painter = painterResource(R.drawable.eventify_group_1),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFF858591)),
                contentScale = ContentScale.FillHeight, // Adjust to fill the container's height
                modifier = Modifier
                    .fillMaxHeight() // Ensure the image takes up full height
                    .weight(0.4f) // Let the image take 45% of the space
            )
        }
    }
}


@Preview(name = "MyPastEventCard")
@Composable
private fun PreviewMyPastEventCard() {
    EventifyTheme {
        FinishedEventCard(
            event = ShortEventItem(
                id = "",
                title = "",
                description = "",
                start = 1231313,
                end = 231231312
            )
        )
    }
}
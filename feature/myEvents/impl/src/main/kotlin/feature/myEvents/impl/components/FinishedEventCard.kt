package feature.myEvents.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp

import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FinishedEventCard(
    event: ShortEventItem,
    onClick: ((String) -> Unit)? = null,
    onFeedbackAction: ((String) -> Unit)? = null,
    showFeedbackButton: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
                .copy(alpha = .5f)
        ),
        onClick = { onClick?.invoke(event.id) },
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Ensures height matches content
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Left Column (Event Info)
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(10.dp)// Ensure it fills the container's height
                        .weight(.6f) // Let this column take 55% of the space
                ) {
                    EventCardTitle(
                        text = event.title,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                        .weight(0.4f) // Let the image take 45% of the space
                )
            }
            if (showFeedbackButton){
                Button(
                    onClick = { onFeedbackAction?.invoke(event.id) },
                    shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp),
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.feedback_action),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


@Preview(name = "MyPastEventCard", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "MyPastEventCard", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewMyPastEventCardDark() {
    EventifyTheme {
        FinishedEventCard(
            event = ShortEventItem(
                id = "",
                title = LoremIpsum(12)
                    .values
                    .joinToString(),
                description = "",
                start = Random.nextLong(),
                end = Random.nextLong(),
                location = LoremIpsum(2).values.joinToString(),
                state = EventState.FINISHED,
            )
        )
    }
}

@Preview(name = "MyPastEventCard", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "MyPastEventCard", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewMyPastEventCardDarkWithButton() {
    EventifyTheme {
        FinishedEventCard(
            showFeedbackButton = true,
            event = ShortEventItem(
                id = "",
                title = LoremIpsum(12)
                    .values
                    .joinToString(),
                description = "",
                start = 1231313,
                end = 231231312,
                location = LoremIpsum(2).values.joinToString(),
                state = EventState.FINISHED,
            )
        )
    }
}
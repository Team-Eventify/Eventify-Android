package feature.myEvents.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import core.common.extentions.DateTimePattern
import core.common.extentions.durationUtcFormatted
import core.common.extentions.toUtcFormat
import data.models.EventState
import domain.models.ShortEventItem
import uikit.EventifyTheme
import uikit.TypographyKit
import uikit.components.FinishedEventInfoChip
import com.example.eventify.uikit.R as UiKitR
import kotlin.random.Random


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
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(.7f)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = event.title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = TypographyKit.bodyMedium,
                    overflow = TextOverflow.StartEllipsis,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
                ) {
                    FinishedEventInfoChip(text = event.start.toUtcFormat(DateTimePattern.ShortNamedDate))
                    FinishedEventInfoChip(text = durationUtcFormatted(event.start, event.end))
                    FinishedEventInfoChip(text = event.location)
                }

            }

            Image(
                painter = painterResource(UiKitR.drawable.eventify_group_1),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFF858591)),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .align(Alignment.BottomEnd)
            )

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
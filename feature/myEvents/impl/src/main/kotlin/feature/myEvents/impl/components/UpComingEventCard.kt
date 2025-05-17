package feature.myEvents.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import core.common.extentions.asDate
import core.common.extentions.asTime
import data.models.EventState
import domain.models.ShortEventItem
import uikit.EventifyTheme
import uikit.TypographyKit
import uikit.components.UpComingEventInfoTag
import java.util.UUID
import com.example.eventify.uikit.R as UiKitR


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UpComingEventCard(
    event: ShortEventItem,
    modifier: Modifier = Modifier,
    onClick: ((String) -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        onClick = { onClick?.invoke(event.id) },
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
                    .weight(.6f)
            ) {
                Text(
                    text = event.title,
                    style = TypographyKit.bodyMedium,
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    overflow = FlowRowOverflow.Visible,
                    maxLines = 2
                ) {
                    UpComingEventInfoTag(text = event.start.asDate())
                    UpComingEventInfoTag(text = event.start.asTime())
                    UpComingEventInfoTag(text = event.location)
                }

            }

            // Right Image
            Image(
                painter = painterResource(UiKitR.drawable.eventify_group_1),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f)
            )
        }
    }
}


@Preview(name = "UpComingEventCard", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "UpComingEventCard", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewMyEventCardLight() {
    EventifyTheme {
        UpComingEventCard(
            event = ShortEventItem(
                id = UUID.randomUUID().toString(),
                title = LoremIpsum(3)
                    .values
                    .toList()
                    .joinToString { it },
                description = "",
                start = 1231313,
                end = 231231312,
                location = LoremIpsum(2).values.joinToString(),
                state = EventState.PUBLISHED,
            )
        )
    }
}
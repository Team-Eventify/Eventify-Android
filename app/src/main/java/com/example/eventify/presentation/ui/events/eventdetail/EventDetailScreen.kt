package com.example.eventify.presentation.ui.events.eventdetail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import com.example.eventify.R
import com.example.eventify.domain.models.Category
import com.example.eventify.domain.models.EventWithCategories
import com.example.eventify.presentation.ui.events.eventdetail.components.ImagePager
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.ChipInfo
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.buttons.PrimaryDeclineButton
import com.example.eventify.presentation.ui.shared.CategoryTagChip
import com.example.eventify.presentation.ui.shared.EventImage
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.asDate
import com.example.eventify.presentation.utils.asTime
import com.example.eventify.presentation.utils.toColorOrNull
import java.util.UUID

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventDetailScreen(
    state: EventDetailState,
    actions: EventDetailActions,
    imageLoader: ImageLoader,
) {
    val pagerState = rememberPagerState(pageCount = { state.event?.pictures?.size ?: 0})

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        ImagePager(
            pagerState = pagerState
        ){ page ->
            val uri = state.event?.pictures?.get(page)
            EventImage(
                uri = "https://eventify.website/api/v1/files/$uri",
                imageLoader = imageLoader,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Start)
            ) {
                ChipInfo(text = state.event?.start?.asDate() ?: "")
                ChipInfo(text = state.event?.start?.asTime() ?: "")

                state.event!!.categories.forEach{ tag ->
                    CategoryTagChip(
                        text = tag.title,
                        color = tag.color.toColorOrNull()!!
                    )
                }
            }

            BodyText(text = state.event!!.description)
            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = stringResource(R.string.organizer),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.misis_logo),
                    contentDescription = "organizer logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(40.dp)
                )
                Text(text = "MISIS", color = MaterialTheme.colorScheme.onSecondary, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (state.event.subscribed) {
                PrimaryDeclineButton(onClick = actions.onUnsubscribe) {
                    PrimaryButtonText(text = "Отменить запись на мероприятие")
                }
            } else {
                PrimaryButton(onClick = actions.onSubscribe) {
                    PrimaryButtonText(text = "Я пойду!")
                }
            }

//            PrimaryButton(onClick = actions.goToRatePage) {
//                PrimaryButtonText(stringResource(R.string.to_rate))
//            }
        }
    }
}


@Composable
@Preview(name = "EventDetailDark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "EventDetailLight", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun EventDetailScreenLightPreview() {
    EventifyTheme() {
        Scaffold { _ ->
            EventDetailScreen(
                state = EventDetailState(
                    event = EventWithCategories(
                        id = UUID.randomUUID().toString(),
                        title = LoremIpsum(5).values.joinToString(),
                        description = LoremIpsum(50).values.joinToString(),
                        start = 0,
                        end = 0,
                        state = "",
                        capacity = 0,
                        location = "",
                        cover = "",
                        subscribed = false,
                        categories = List(5){
                            Category(
                                id = UUID.randomUUID().toString(),
                                color = "#A3C7FF",
                                cover = "",
                                title = LoremIpsum(1).values.joinToString()
                            )
                        },
                        organizationID = UUID.randomUUID().toString(),
                        pictures = emptyList()
                    )
                ),
                actions = EventDetailActions(
                    navigateUp = {},
                    onSubscribe = {},
                    onUnsubscribe = {},
                ),
                imageLoader = ImageLoader(LocalContext.current)
            )
        }
    }
}

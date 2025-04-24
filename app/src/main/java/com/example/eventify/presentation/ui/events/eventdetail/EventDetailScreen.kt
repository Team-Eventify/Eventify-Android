package com.example.eventify.presentation.ui.events.eventdetail

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.domain.models.Category
import com.example.eventify.domain.models.EventDetail
import com.example.eventify.domain.models.EventState
import com.example.eventify.domain.models.FullEventDetail
import com.example.eventify.domain.models.Organization
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.events.eventdetail.components.ImagePager
import com.example.eventify.presentation.ui.events.eventdetail.components.OrganizationInfoPanel
import com.example.eventify.presentation.ui.common.BodyText
import com.example.eventify.presentation.ui.common.ChipInfo
import com.example.eventify.presentation.ui.common.CategoryTagChip
import com.example.eventify.presentation.ui.common.EventImage
import com.example.eventify.presentation.ui.events.eventdetail.components.EventActionButton
import com.example.eventify.presentation.ui.events.eventdetail.state.EventDetailListener
import com.example.eventify.presentation.ui.events.eventdetail.state.EventDetailUiState
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions
import com.example.eventify.presentation.utils.asDate
import com.example.eventify.presentation.utils.asTime
import com.example.eventify.presentation.utils.toColorOrNull
import java.util.UUID

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventDetailScreen(
    state: EventDetailUiState.ShowEvent,
    actions: EventDetailListener,
) {
    val pagerState = rememberPagerState(pageCount = { state.event.eventInfo.pictures.size})
    val dimmentions = LocalDimentions.current
    val topBarState = LocalTopBarState.current


    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = state.event.eventInfo.title,
                size = TopBarSize.MEDIUM,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = actions::navigateUp
                )
            )
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        ImagePager(
            pagerState = pagerState
        ){ page ->
            val url = state.event.eventInfo.pictures[page]
            EventImage(
                uri = url,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Column(
            modifier = Modifier
                .padding(dimmentions.windowPaddings)
        ) {

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Start)
            ) {
                ChipInfo(text = state.event.eventInfo.start.asDate())
                ChipInfo(text = state.event.eventInfo.start.asTime())
                ChipInfo(text = state.event.eventInfo.location)

                state.event.categories.forEach{ tag ->
                    CategoryTagChip(
                        text = tag.title,
                        color = tag.color.toColorOrNull()!!
                    )
                }
            }

            BodyText(text = state.event.eventInfo.description)
            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = stringResource(R.string.organizer),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            OrganizationInfoPanel(
                organization = state.event.organization,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(20.dp))

            EventActionButton(
                eventState = state.event.eventInfo.state,
                isSubscribed = state.event.eventInfo.subscribed,
                onClick = actions::onActionClick
            )


//            if (state.event.eventInfo.subscribed) {
//                PrimaryDeclineButton(
//                    onClick = actions::onUnsubscribe,
//                    enabled = !state.event.eventInfo.isFinished,
//                ) {
//                    PrimaryButtonText(text = "Отменить запись на мероприятие")
//                }
//            } else {
//                PrimaryButton(
//                    onClick = actions::onSubscribe,
//                    enabled = !state.event.eventInfo.isFinished,
//                ) {
//                    PrimaryButtonText(text = "Я пойду!")
//                }
//            }

//            PrimaryButton(onClick = actions.goToRatePage) {
//                PrimaryButtonText(stringResource(R.string.to_rate))
//            }
        }
    }
}


@Composable
@Preview(name = "EventDetailDark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "EventDetailLight", uiMode = Configuration.UI_MODE_NIGHT_NO)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun EventDetailScreenLightPreview() {
    EventifyTheme {
        Scaffold {  _ ->
            EventDetailScreen(
                state = EventDetailUiState.ShowEvent(
                    event = FullEventDetail(
                        eventInfo = EventDetail(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(5).values.joinToString(),
                            description = LoremIpsum(50).values.joinToString(),
                            start = 0,
                            state = EventState.PUBLISHED,
                            end = 0,
                            capacity = 0,
                            location = "",
                            cover = "",
                            subscribed = false,

                            organizationID = UUID.randomUUID().toString(),
                            pictures = emptyList(),
                        ),
                        categories = List(5){
                            Category(
                                id = UUID.randomUUID().toString(),
                                color = "#A3C7FF",
                                cover = "",
                                title = LoremIpsum(1).values.joinToString()
                            )
                        },
                        organization = Organization(
                            id = UUID.randomUUID().toString(),
                            title = LoremIpsum(2).values.joinToString(),
                            description = LoremIpsum(4).values.joinToString(),
                            photoUrl = UUID.randomUUID().toString()
                        )
                    )
                ),
                actions = object : EventDetailListener {
                    override fun navigateUp() = Unit
                    override fun onActionClick() = Unit
                    override fun goToRatePage() = Unit
                },
            )
        }
    }
}

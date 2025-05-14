package feature.eventDetail.impl.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.common.extentions.asDate
import core.common.extentions.asTime
import core.common.extentions.toColorOrNull
import data.models.Category
import data.models.EventDetail
import data.models.EventState
import data.models.Organization
import domain.models.FullEventDetail
import feature.eventDetail.api.EventDetailListener
import feature.eventDetail.impl.components.EventActionButton
import feature.eventDetail.impl.components.ImagePager
import feature.eventDetail.impl.components.OrganizationInfoPanel
import feature.eventDetail.impl.state.EventDetailUiState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.components.BodyText
import uikit.components.CategoryTagChip
import uikit.components.ChipInfo
import uikit.components.EventImage
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.TopBarAction
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import uikit.utils.conditional
import java.util.UUID
import com.example.eventify.uikit.R as UiKitR


@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun EventDetailScreen(
    state: EventDetailUiState.ShowEvent,
    actions: EventDetailListener,
) {
    val pagerState = rememberPagerState(pageCount = { state.event.eventInfo.pictures.size})
    val dimmentions = LocalDimentions.current
    val topBarState = LocalTopBarState.current
    var isShowFullSizeImage by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = state.event.eventInfo.title,
                size = TopBarSize.MEDIUM,
                leftAction = TopBarAction(
                    iconRes = UiKitR.drawable.ic_chevron_right,
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
            pagerState = pagerState,
            key = state.event.eventInfo.pictures::get
        ){ page ->
            EventImage(
                uri = state.event.eventInfo.pictures[page],
                modifier = Modifier
                    .fillMaxWidth()
                    .conditional(!isShowFullSizeImage) {
                        height(200.dp)
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        isShowFullSizeImage = !isShowFullSizeImage
                    }
            )
        }
        Column(
            modifier = Modifier.padding(dimmentions.windowPaddings)
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
                text = stringResource(UiKitR.string.organizer),
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
                        categories = List(5) {
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

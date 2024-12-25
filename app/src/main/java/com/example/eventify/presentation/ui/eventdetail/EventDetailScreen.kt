package com.example.eventify.presentation.ui.eventdetail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.domain.models.EventWithCategories
import com.example.eventify.presentation.ui.eventdetail.components.ImagePager
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.ChipInfo
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.buttons.PrimaryDeclineButton
import com.example.eventify.presentation.ui.shared.TagChip
import com.example.eventify.presentation.ui.theme.EventifyTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventDetailScreen(
    state: EventDetailState,
    actions: EventDetailActions,
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ImagePager(
            listOf(
                painterResource(R.drawable.default_event_image),
                painterResource(R.drawable.misis_cj_image),
                painterResource(R.drawable.default_event_image),
            )
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Start)
            ) {
                ChipInfo(text = "2 марта")
                ChipInfo(text = "17:30")

                state.event!!.categories.forEach{ tag ->
                    TagChip(text = tag.title)
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

            if (state.event.subscribed){
                PrimaryDeclineButton(onClick = actions.onUnsubscribe) {
                    PrimaryButtonText(text = "Отменить запись на мероприятие")
                }
            } else {
                PrimaryButton(onClick = actions.onSubscribe) {
                    PrimaryButtonText(text = "Я пойду!")
                }
            }
        }
    }
}

@Composable
@Preview(name = "EventDetailDark")
private fun EventDetailScreenDarkPreview() {
    EventifyTheme(darkTheme = true) {
        Scaffold { _ ->
            EventDetailScreen(
                state = EventDetailState(
                    event = EventWithCategories(
                        id = "",
                        title = "День открытых дверей",
                        description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в одном из лучших технических университетов России, научной деятельности под руководством учёных с мировым именем, образовательных проектах и карьерных возможностях, которые предлагает вуз, яркой студенческой жизни в Москве. В Университете МИСИС каждый студент сможет получить профессию будущего и быть востребованным лучшими российскими и зарубежными работодателями, раскрыть свой потенциал, благодаря формируемой в вузе экосреде креативности и творчества!",
                        createdAt = 0,
                        modifiedAt = 0,
                        start = 0,
                        end = 0,
                        moderated = false,
                        state = "",
                        capacity = 0,
                        ownerID = "",
                        location = "",
                        cover = "",
                        subscribed = false,
                        categories = emptyList()
                    )
                ),
                actions = EventDetailActions(
                    navigateUp = {},
                    onUnsubscribe = {},
                    onSubscribe = {}
                )
            )
        }
    }
}

@Composable
@Preview(name = "EventDetailLight")
private fun EventDetailScreenLightPreview() {
    EventifyTheme() {
        Scaffold { _ ->
            EventDetailScreen(
                state = EventDetailState(
                    event = EventWithCategories(
                        id = "",
                        title = "День открытых дверей",
                        description = "Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в одном из лучших технических университетов России, научной деятельности под руководством учёных с мировым именем, образовательных проектах и карьерных возможностях, которые предлагает вуз, яркой студенческой жизни в Москве. В Университете МИСИС каждый студент сможет получить профессию будущего и быть востребованным лучшими российскими и зарубежными работодателями, раскрыть свой потенциал, благодаря формируемой в вузе экосреде креативности и творчества!",
                        createdAt = 0,
                        modifiedAt = 0,
                        start = 0,
                        end = 0,
                        moderated = false,
                        state = "",
                        capacity = 0,
                        ownerID = "",
                        location = "",
                        cover = "",
                        subscribed = false,
                        categories = emptyList()

                    )
                ),
                actions = EventDetailActions(
                    navigateUp = {},
                    onUnsubscribe = {},
                    onSubscribe = {}
                )
            )
        }
    }
}

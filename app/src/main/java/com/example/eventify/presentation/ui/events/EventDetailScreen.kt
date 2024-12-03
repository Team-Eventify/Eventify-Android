package com.example.eventify.presentation.ui.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.data.models.EventInfo
import com.example.eventify.presentation.ui.shared.ActionPrimaryText
import com.example.eventify.presentation.ui.shared.ActionText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.ChipInfo
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.ShortenedBodyText
import com.example.eventify.presentation.ui.shared.TagChip
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.viewmodels.EventDetailViewmodel
import kotlinx.coroutines.launch

@Composable
fun EventDetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: EventDetailViewmodel = hiltViewModel()
) {
    if (viewModel.event != null) {
        EventDetailScreenComponent(
            event = viewModel.event!!,
            onSubmit = {},
            onBackAction = {
                navController.popBackStack()
            }
        )
    } else {
        Text(text = "Not Found")
    }

}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreenComponent(
    event: EventInfo,
    onSubmit: (String) -> Unit,
    onBackAction: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = event.title)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackAction
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_chevron_right),
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*Share*/ }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_turn_up_right),
                            contentDescription = ""
                        )
                    }

                }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(top = paddingValue.calculateTopPadding())
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
                var textState by remember { mutableStateOf(true) }

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Start)
                ) {
                    ChipInfo(text = "2 марта")
                    ChipInfo(text = "17:30")
                    TagChip(text = "Тэг 1")
                    TagChip(text = "Тэг 2")
                    TagChip(text = "Тэг 3")
                }

                ShortenedBodyText(
                    text = event.description,
                    textState = textState
                )
                ActionPrimaryText(
                    text = if (textState) "Полное описание" else "Скрыть описание",
                    onClick = { textState = !textState }
                )
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
                PrimaryButton(onClick = { }) {
                    PrimaryButtonText(text = "Я пойду!")
                }


            }
        }

    }

}


@Composable
fun ImagePager(
    images: List<Painter>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { images.count() })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier.fillMaxWidth()
    ) {

        HorizontalPager(
            state = pagerState,
            pageSpacing = 15.dp,
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            // Our page content
            Image(
                painter = images[page],
                contentDescription = "sdas",
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.scrollToPage(iteration)
                            }
                        }
                )
            }
        }
    }
}

@Preview(name = "EventDetailScreen", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewEventDetailScreen() {
    EventDetailScreenComponent(
        event = EventInfo(
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
            ownerID = ""
        ),
        onBackAction = {},
        onSubmit = {}
    )
}
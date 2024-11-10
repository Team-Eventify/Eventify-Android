package com.example.eventify.presentation.ui.events

import android.icu.text.CaseMap.Title
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.ActionPrimaryText
import com.example.eventify.presentation.ui.shared.ActionText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.ShortenedBodyText
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.viewmodels.EventDetailViewmodel
import kotlinx.coroutines.launch

@Composable
fun EventDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: EventDetailViewmodel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.loadEvent()
    }
    Column {
        ImagePager(
            listOf(
                painterResource(R.drawable.default_event_image),
                painterResource(R.drawable.misis_cj_image),
                painterResource(R.drawable.default_event_image),
            )
        )
        Column(
            modifier
                .padding(10.dp)
        ) {
            var textState by remember { mutableStateOf(true) }

            ShortenedBodyText(
                text = viewModel.event?.description ?: "[eq",
                textState = textState
            )
            ActionPrimaryText(
                text = if (textState) "Полное описание" else "Скрыть описание",
                onClick = { textState = !textState }
            )
            Spacer(modifier.height(20.dp))
            PrimaryButton(onClick = { }) {
                PrimaryButtonText(text = "Я пойду!")
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
    EventDetailScreen()
}
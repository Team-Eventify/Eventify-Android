package com.example.eventify.presentation.ui.myevents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventify.R
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.viewmodels.MyEventsViewModel

@Composable
fun MyEventsScreen(
    myEventsViewModel: MyEventsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    if (myEventsViewModel.upComingEvents.isEmpty() && myEventsViewModel.finishedEvents.isEmpty()){
        EmptyEventsComponent()
    }
    else{
        MyEventsScreenComponent(
            upComingEvents = myEventsViewModel.upComingEvents,
            finishedEvents = myEventsViewModel.finishedEvents
        )
    }

}

@Composable
fun MyEventsScreenComponent(
    upComingEvents: List<ShortEventItem>,
    finishedEvents: List<ShortEventItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (upComingEvents.isNotEmpty()) {
            HeadingText(text = "Предстоящие мероприятия")
        }
        Spacer(modifier = Modifier.height(20.dp))
        upComingEvents.forEach { event ->
            UpComingEventCard(event = event)
            Spacer(modifier = Modifier.height(10.dp))
        }

        if (finishedEvents.isNotEmpty()){
            HeadingText(text = "Прошедшие мероприятия")
        }
        Spacer(modifier = Modifier.height(20.dp))
        finishedEvents.forEach { event ->
            FinishedEventCard(event = event)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun EmptyEventsComponent(
    text: String = "Нет предстоящих мероприятий"
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.bookmark),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(.2f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            fontSize = 20.sp,
            text = text,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(name = "MyEventsScreen", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMyEventsScreen() {

    MyEventsScreenComponent(
        finishedEvents = listOf(
            ShortEventItem(
                id = "",
                title = "День первокурсника",
                description = "",
                start = 1231313,
                end = 231231312
            ),

        ),
        upComingEvents = listOf(
            ShortEventItem(
                id = "",
                title = "День открытых дверей",
                description = "",
                start = 1231313,
                end = 231231312
            ),
            ShortEventItem(
                id = "",
                title = "День первокурсника",
                description = "",
                start = 1231313,
                end = 231231312
            )
        )
    )
}

@Preview(name = "EmptyEvents", showSystemUi = true, showBackground = true)
@Composable
fun PreviewEmptyEvents() {
    EmptyEventsComponent()
}
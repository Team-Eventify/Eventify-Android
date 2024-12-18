package com.example.eventify.presentation.ui.myevents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun EmptyMyEventsScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_my_events_icon),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(.2f)
        )
        Text(
            text = "Предстоящих событий нет",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        AnnotationText(
            text = "Сейчас нет запланированных мероприятий. Загляните позже!",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "EmptyMyEventsScreen", showSystemUi = true)
@Composable
private fun PreviewEmptyMyEventsScreen() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            EmptyMyEventsScreen()
        }
    }
}
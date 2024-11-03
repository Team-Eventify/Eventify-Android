package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.eventify.R

@Composable
fun EventCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painterResource(R.drawable.default_event_image),
            contentDescription = "Nothing",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
        EventCardTitle("День открытых дверей")
        BodyText("Дни открытых дверей — это уникальная возможность для старшеклассников больше узнать о специальностях, которым обучают в Унивеситете МИСИС.")
    }
}

@Preview(name = "EventCard", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewEventCard() {
    EventCard()
}
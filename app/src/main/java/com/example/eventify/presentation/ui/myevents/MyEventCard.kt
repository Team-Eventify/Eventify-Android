package com.example.eventify.presentation.ui.myevents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.EventCardTitle

@Composable
fun MyEventCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(0.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .padding(vertical = 30.dp)
                    .padding(start = 10.dp)

            ) {
                EventCardTitle(text = "День открытых дверей университета МИСИС")
            }
            Image(
                painter = painterResource(id = R.drawable.eventify_group_1),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
            )
        }
    }
}


@Composable
fun MyPastEventCard(
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFC7C7C7)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                        .padding(vertical = 30.dp)
                        .padding(start = 10.dp)

                ) {
                    EventCardTitle(text = "День открытых дверей университета МИСИС")
                }
                Image(
                    painter = painterResource(id = R.drawable.eventify_vector_69),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                )
            }
        }

    }
}

@Preview(name = "MyEventCard")
@Composable
private fun PreviewMyEventCard() {
    MyEventCard()
}

@Preview(name = "MyPastEventCard")
@Composable
private fun PreviewMyPastEventCard() {
    MyPastEventCard()
}

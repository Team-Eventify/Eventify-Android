package com.example.eventify.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun BorderedImage(
    painter: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surfaceBright,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(.17f)
                .padding(10.dp)
                .then(modifier)
            ,
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(name = "BorderedImage")
@Composable
private fun PreviewBorderedImage() {
    EventifyTheme {
        Surface {
            BorderedImage(
                painter = painterResource(R.drawable.ic_no_internet)
            )
        }
    }

}
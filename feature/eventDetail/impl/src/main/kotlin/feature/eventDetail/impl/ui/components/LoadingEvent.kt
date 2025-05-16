package feature.eventDetail.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.components.shimmer


@Composable
fun LoadingEvent() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .shimmer(showShimmer = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))

        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 30.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))

            )
            Box(
                modifier = Modifier
                    .size(80.dp, 30.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))

            )
            Box(
                modifier = Modifier
                    .size(80.dp, 30.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))

            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(.3f)
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))

        )

    }
}

@Preview(name = "LoadingEvent")
@Composable
private fun PreviewLoadingEvent() {
    LoadingEvent()
}
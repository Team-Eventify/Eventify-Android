package uikit.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.eventify.uikit.R
import uikit.LocaleImageLoader
import uikit.TypographyKit
import uikit.components.EventInfoTag
import uikit.components.shimmer
import uikit.space10
import uikit.space12
import uikit.space6


@Composable
fun EventCard(
    title: String,
    description: String,
    duration: String,
    location: String,
    startTime: String,
    coverId: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val localeImageLoader = LocaleImageLoader.current

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
        ) {
            var showShimmer by remember { mutableStateOf(false) }

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://eventify.website/api/v1/files/$coverId")
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.underfind_event_image),
                placeholder = painterResource(R.drawable.underfind_event_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                imageLoader = localeImageLoader,
                onLoading = {
                    showShimmer = true
                },
                onError = {
                    showShimmer = false
                },
                onSuccess = {
                    showShimmer = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer(
                        showShimmer = showShimmer,
                        blendMode = BlendMode.SrcAtop
                    )
            )

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    EventInfoTag(startTime)
                    EventInfoTag(duration)
                    EventInfoTag(location)
                }

                Spacer(Modifier.height(space6))

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = TypographyKit.Heading.medium,
                )
                Spacer(Modifier.height(space10))
                Text(
                    text = description,
                    style = TypographyKit.Body.regular,
                    maxLines = 7,
                )
            }
        }
    }
}

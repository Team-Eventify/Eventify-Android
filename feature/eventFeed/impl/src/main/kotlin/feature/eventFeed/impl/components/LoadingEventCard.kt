package feature.eventFeed.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.components.shimmer
import com.example.eventify.uikit.R as UiKitR
import uikit.EventifyTheme

@Composable
internal fun LoadingEventCard(
    blendMode: BlendMode = BlendMode.SrcAtop
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .shimmer(
                showShimmer = true,
                blendMode = blendMode
            )
    ) {
        Image(
            painter = painterResource(UiKitR.drawable.underfind_event_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))

        )
        Box(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
        )
    }
}



@Preview(name = "LoadingEventCard Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "LoadingEventCard Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingEventCard() {
    EventifyTheme {
        LoadingEventCard()
    }
}
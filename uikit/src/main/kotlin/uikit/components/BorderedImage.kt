package uikit.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme
import uikit.space10
import uikit.space12
import com.example.eventify.uikit.R as UiKitR


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
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(space12)
                .size(48.dp)
                .then(modifier)
        )
    }
}

@Preview(name = "BorderedImage", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "BorderedImage", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewBorderedImage() {
    EventifyTheme {
        Surface {
            BorderedImage(
                painter = painterResource(UiKitR.drawable.ic_no_internet)
            )
        }
    }

}
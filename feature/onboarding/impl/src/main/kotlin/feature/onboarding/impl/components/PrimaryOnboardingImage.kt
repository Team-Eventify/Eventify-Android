package feature.onboarding.impl.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun PrimaryOnboardingImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
        alignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surface)
            .composed { modifier }
    )
}

@Preview(name = "PrimaryOnboardingImage")
@Composable
private fun PreviewPrimaryOnboardingImage() {
    EventifyTheme(darkTheme = true) {
        Surface{
            PrimaryOnboardingImage(
                painter = painterResource(R.drawable.ic_launcher_foreground)
            )
        }
    }
}
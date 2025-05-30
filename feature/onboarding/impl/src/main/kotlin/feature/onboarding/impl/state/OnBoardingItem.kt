package feature.onboarding.impl.state

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter


@Stable
data class OnBoardingItem(
    val primaryImage: Painter,
    val title: String,
    val body: String
)


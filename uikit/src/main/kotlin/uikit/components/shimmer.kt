package uikit.components

import android.annotation.SuppressLint
import androidx.annotation.IntRange
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.withSaveLayer
import kotlin.math.cos
import kotlin.math.sin

private val gradientColorsWhite = listOf(
    Color(0xFFFAFAFA),
    Color(0xFFF2F2F2),
    Color(0xFFE3E3E3),
    Color(0xFFF2F2F2),
    Color(0xFFFAFAFA),
)

private val gradientColorsDark = listOf(
    Color(0xFF202020),
    Color(0xFF2B2B2B),
    Color(0xFF8F8B8B),
    Color(0xFF2B2B2B),
    Color(0xFF202020),
)

@Stable
@SuppressLint("SuspiciousModifierThen")
@Composable
fun Modifier.shimmer(
    showShimmer: Boolean = true,
    @IntRange(from = 0, to = 360) tiltAngle: Int = 30,
    durationMillis: Int = 1000,
    blendMode: BlendMode = BlendMode.SrcAtop
): Modifier {
    if (!showShimmer) return this

    val isDarkTheme = true

    val gradient = gradientColorsDark.takeIf { isDarkTheme } ?: gradientColorsWhite

    val transition = rememberInfiniteTransition(label = "shimmer animation transition")
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer translate animation"
    )
    val radians = Math.toRadians(tiltAngle.toDouble()).toFloat()

    return this then drawWithContent {
        with(drawContext.canvas) {
            val brush = Brush.linearGradient(
                colors = gradient,
                start = Offset(
                    size.width * translateAnimation * cos(radians),
                    size.height * translateAnimation * sin(radians)
                ),
                end = Offset(
                    2 * size.width * translateAnimation * cos(radians),
                    2 * size.height * translateAnimation * sin(radians)
                )
            )
            withSaveLayer(
                bounds = size.toRect(),
                paint = Paint()
            ) {
                drawContent()
                drawRect(
                    brush = brush,
                    blendMode = blendMode
                )
            }
        }
    }
}
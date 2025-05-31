package uikit.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.annotation.IntRange
import androidx.compose.ui.unit.dp
import uikit.space10

@Composable
fun SkeletonBox(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    shimmer: Boolean = false,
    @IntRange(from = 0, to = 360) tiltAngle: Int = 30,
    durationMillis: Int = 1000,
    blendMode: BlendMode = BlendMode.SrcAtop,
    color: Color = MaterialTheme.colorScheme.surface
) {
    SkeletonBox(
        shimmer = shimmer,
        blendMode = blendMode,
        durationMillis = durationMillis,
        tiltAngle = tiltAngle,
        color = color,
        modifier = Modifier
            .size(width, height)
            .then(modifier)
    )
}


@Composable
fun SkeletonBox(
    modifier: Modifier = Modifier,
    shimmer: Boolean = false,
    @IntRange(from = 0, to = 360) tiltAngle: Int = 30,
    durationMillis: Int = 1000,
    blendMode: BlendMode = BlendMode.SrcAtop,
    color: Color = MaterialTheme.colorScheme.surface
) {
    Box(
        modifier = Modifier
            .shimmer(
                showShimmer = shimmer,
                durationMillis = durationMillis,
                blendMode = blendMode,
                tiltAngle = tiltAngle,
            )
            .then(modifier)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))


    )
}

@Composable
fun SkeletonBox(
    height: Dp,
    modifier: Modifier = Modifier,
    shimmer: Boolean = false,
    @IntRange(from = 0, to = 360) tiltAngle: Int = 30,
    durationMillis: Int = 1000,
    blendMode: BlendMode = BlendMode.SrcAtop,
    color: Color = MaterialTheme.colorScheme.surface
) {
    SkeletonBox(
        shimmer = shimmer,
        blendMode = blendMode,
        durationMillis = durationMillis,
        tiltAngle = tiltAngle,
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .then(modifier)
    )
}

@Preview
@Composable
private fun SkeletonBoxPreviewMaxWith() {
    SkeletonBox(height = 50.dp, shimmer = true)
}

@Preview
@Composable
private fun SkeletonBoxPreviewFullSize() {
    SkeletonBox(width = 50.dp, height = 50.dp, shimmer = true)
}

@Preview
@Composable
private fun SkeletonBoxPreview() {
    SkeletonBox(modifier = Modifier.size(30.dp), shimmer = true)
}
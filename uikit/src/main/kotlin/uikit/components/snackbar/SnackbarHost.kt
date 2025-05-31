package uikit.components.snackbar

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uikit.EventifyTheme
import uikit.LocalSnackbarState
import uikit.TypographyKit
import uikit.space10
import uikit.space18
import uikit.space28
import uikit.space64
import com.example.eventify.uikit.R as UiKitR


@Composable
fun SnackbarHost(
    modifier: Modifier = Modifier
) {
    val controller = LocalSnackbarState.current
    val snackbarMessage by controller.current.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Companion.TopCenter
    ) {
        AnimatedVisibility(
            visible = (snackbarMessage?.isVisible ?: false),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight }
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight }
            ) + fadeOut()
        ) {
            snackbarMessage?.let { state ->
                AppSnackbar(
                    title = state.title,
                    description = state.description,
                    iconResId = state.style.iconResId,
                    colors = state.style.colors(),
                    modifier = Modifier
                        .then(modifier)
                )
            }
        }
    }
}


@Composable
fun AppSnackbar(
    title: String,
    description: String? = null,
    @DrawableRes iconResId: Int? = null,
    colors: SnackbarColors = SnackbarColors.default(),
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(27.5.dp),
        color = colors.containerColor,
        modifier = Modifier.Companion
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.Companion
                .padding(
                    vertical = space18,
                    horizontal = space28
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {

            iconResId?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = colors.iconTint,
                    modifier = Modifier
                        .size(space28)
                )
            }

            Spacer(modifier = Modifier.Companion.width(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = colors.textColor,
                    style = TypographyKit.Body.regular.copy(
                        textAlign = TextAlign.Center
                    ),
                )
                description?.let {
                    Text(
                        text = it,
                        color = colors.textColor.copy(
                            alpha = .7f
                        ),
                        style = TypographyKit.Body.regular.copy(
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppSnackbarPreview(
    @PreviewParameter(AppSnackbarPreviewParameters::class) message: SnackbarMessage
) {
    EventifyTheme {
        AppSnackbar(
            title = message.title,
            description = message.description,
            iconResId = message.style.iconResId,
            colors = message.style.colors(),
        )
    }
}


private class AppSnackbarPreviewParameters : PreviewParameterProvider<SnackbarMessage> {
    override val values: Sequence<SnackbarMessage>
        get() = sequenceOf(
//            SnackbarMessage(
//                title = "Title",
//                description = "Description",
//                type = SnackbarType.Info,
//            ),

            SnackbarMessage(
                title = "Title",
                description = "Description",
                style = SnackbarStyle.Success(),
            ),

            SnackbarMessage(
                title = "Title",
                description = "Description",
                style = SnackbarStyle.Error,
            ),
        )

}
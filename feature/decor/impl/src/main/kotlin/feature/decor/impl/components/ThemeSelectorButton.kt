package feature.decor.impl.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme
import uikit.TypographyKit
import uikit.space12
import com.example.eventify.uikit.R as UiKitR
import com.example.eventify.feature.decor.impl.R as DecorR


@Composable
fun ThemeSelectorButton(
    isActiveTheme: Boolean,
    @DrawableRes iconTheme: Int,
    @StringRes titleTheme: Int,
    modifier: Modifier = Modifier,
    changeTheme: (() -> Unit),
) {

    val currentColor = MaterialTheme.colorScheme.onSurface.takeIf { isActiveTheme } ?: MaterialTheme.colorScheme.onSurfaceVariant


    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary.takeIf { isActiveTheme }
                    ?: MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable { changeTheme() }

            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(space12)
        ) {
            Icon(
                painter = painterResource(iconTheme),
                contentDescription = null,
                tint = currentColor,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )

            Text(
                text = stringResource(titleTheme),
                style = TypographyKit.Body.regular,
                overflow = TextOverflow.Ellipsis,
                color = currentColor,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )
        }
    }
}


@Preview
@Composable
private fun IconThemeComponentPreview() {
    EventifyTheme {
        ThemeSelectorButton(
            isActiveTheme = false,
            iconTheme = UiKitR.drawable.circle_lefthalf_filled,
            titleTheme = DecorR.string.system_theme_ttile,
            changeTheme = {},
            modifier = Modifier
                .size(89.dp),
        )
    }
}

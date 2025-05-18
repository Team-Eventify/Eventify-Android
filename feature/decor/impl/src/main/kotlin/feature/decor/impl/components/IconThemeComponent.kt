package feature.decor.impl.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uikit.TypographyKit


@Composable
fun IconThemeComponent(
    isActiveTheme: Boolean,
    isSystemOrDarkTheme: Boolean,
    iconTheme: Int,
    @StringRes titleTheme: Int,
    changeTheme: (() -> Unit)
) {

    IconButton(
        onClick = changeTheme,
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .padding(5.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary.takeIf { isActiveTheme } ?: MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical=15.dp, horizontal=20.dp),
            ) {
            Icon(
                painter = painterResource(iconTheme),
                contentDescription = null,
                tint = if (isSystemOrDarkTheme) Color.White else Color.Black,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(titleTheme),
                style = TypographyKit.bodyRegular,
            )
        }
    }
}

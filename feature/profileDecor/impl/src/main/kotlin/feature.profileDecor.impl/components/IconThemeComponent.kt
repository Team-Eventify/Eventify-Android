package com.example.eventify.presentation.ui.account.profile_decor.components

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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.uikit.R as UiKitR
import uikit.EventifyTheme
import uikit.components.BodyText

@Composable
fun IconThemeComponent(
    isActiveTheme: Boolean,
    isSystemOrDarkTheme: Boolean,
    iconTheme: Int,
    activeColor: Color,
    @StringRes titleTheme: Int,
    changeTheme: (() -> Unit)
) {

    IconButton(onClick = { changeTheme() }, modifier = Modifier
        .width(120.dp)
        .height(120.dp)
        .padding(5.dp)
        .border(width = 2.dp,
        color = if (isActiveTheme) activeColor else Color.Gray,
        shape = RoundedCornerShape(16.dp)
    )) {
        Column(modifier = Modifier
            .padding(vertical=15.dp, horizontal=20.dp),) {
            Icon(
                painter = painterResource(iconTheme),
                contentDescription = null,
                tint = if (isSystemOrDarkTheme) Color.White else Color.Black,
            )
            Spacer(modifier = Modifier.height(20.dp))
            BodyText(text = stringResource(titleTheme))
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun IconThemeComponentScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            IconThemeComponent(
                isActiveTheme = true,
                isSystemOrDarkTheme = false,
                iconTheme = UiKitR.drawable.circle_lefthalf_filled,
                titleTheme = UiKitR.string.system_theme_ttile,
                activeColor = Color.White,
                changeTheme = {}
            )
        }
    }
}
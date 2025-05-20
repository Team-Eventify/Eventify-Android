package feature.decor.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.common.theme.ThemeType
import feature.decor.impl.components.ThemeSelectorButton
import feature.decor.impl.state.DecorUiState
import feature.decor.impl.state.DecorListener
import feature.decor.impl.state.ThemeTypePreviews
import uikit.EventifyTheme
import com.example.eventify.feature.decor.impl.R as DecorR
import uikit.LocalDimentions
import uikit.TypographyKit
import uikit.space12


@Composable
fun ProfileDecorScreen(
    state: DecorUiState,
    actions: DecorListener
) {
    val dimmentions = LocalDimentions.current
    val isDarkTheme = isSystemInDarkTheme()

    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize()
        .padding(dimmentions.windowPaddings)) {
        Text(
            text = stringResource(DecorR.string.theme_title),
            style = TypographyKit.bodyMedium
        )
        Spacer(modifier = Modifier
            .height(10.dp),)

        Row(
            horizontalArrangement = Arrangement.spacedBy(space12),
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            ThemeTypePreviews.entries.forEach { item ->
                ThemeSelectorButton(
                    isActiveTheme = state.currentTheme == item.themeType,
                    iconTheme = item.iconResId,
                    titleTheme = item.titleResId,
                    changeTheme = {
                        actions.onChangeTheme(item.themeType)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                )


            }



//            IconThemeComponent(isActiveTheme = false,
//                isSystemOrDarkTheme = isDarkTheme,
//                iconTheme = R.drawable.sun_max_fill,
//                titleTheme = R.string.light_theme_title,
//                activeColor = Color.Black,
//                changeTheme = onClick,
//            )
//
//            IconThemeComponent(isActiveTheme = true,
//                isSystemOrDarkTheme = isDarkTheme,
//                iconTheme = R.drawable.moon_fill,
//                titleTheme = R.string.dark_theme_title,
//                activeColor = Color.White,
//                changeTheme = onClick,
//            )
        }
//        HorizontalDivider(modifier = Modifier
//            .padding(vertical=20.dp),)
//
//        Text(
//            text = stringResource(UiKitR.string.icon_logo),
//            style = TypographyKit.bodyMedium
//        )


//        FlowRow(modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 20.dp),
//            horizontalArrangement = Arrangement.SpaceBetween) {
//            uiState.listIcons.forEach { item ->
//                Column(modifier = Modifier
//                    .clickable {
//                    actions.changeIcon(item)
//                }){
//                    Image(
//                        painter = painterResource(item.icon),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .padding(3.dp)
//                            .border(1.dp,
//                                if (item.alias == uiState.activeTypeOfIconAlias) Color(0xFF74F6A7)
//                                else Color.Transparent,
//                                RoundedCornerShape(size = 20.dp),
//                                )
//                            .padding(3.dp)
//                            .border(1.dp,
//                                if (item.alias == uiState.activeTypeOfIconAlias) Color.Black
//                                else Color.Transparent,
//                                RoundedCornerShape(size = 20.dp),
//                                )
//                            .clip(RoundedCornerShape(size = 20.dp))
//                        )
//                    Text(text = stringResource(item.titleRes),
//                        modifier = Modifier.padding(horizontal = 6.dp))
//                }
//            }
//        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileDecorScreenPreview() {
    EventifyTheme {
        ProfileDecorScreen(
            state = DecorUiState(
                currentTheme = ThemeType.SYSTEM
            ),
            actions = object : DecorListener {
                override fun onChangeTheme(theme: ThemeType) = Unit
                override fun navigateBack() = Unit
            }
        )
    }
}


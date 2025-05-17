package feature.profileDecor.impl

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import feature.profileDecor.impl.components.IconThemeComponent
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorRouteListener
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorUiState
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme
import domain.models.LogoIcon
import uikit.EventifyTheme
import com.example.eventify.uikit.R as R

import uikit.LocalDimentions
import uikit.components.SubHeadingText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileDecorScreen(uiState: ProfileDecorUiState, actions: ProfileDecorRouteListener) {
    val dimmentions = LocalDimentions.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(dimmentions.windowPaddings)) {
        SubHeadingText(text = stringResource(R.string.theme_title))
        Spacer(modifier = Modifier
            .height(10.dp),)
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconThemeComponent(isActiveTheme = uiState.activeTypeOfTheme == null,
                isSystemOrDarkTheme = uiState.isSystemOrDarkTheme,
                iconTheme = R.drawable.circle_lefthalf_filled,
                titleTheme = R.string.system_theme_ttile,
                activeColor = Color.White,
                changeTheme = { actions.changeTheme(TypesTheme.SYSTEM_THEME) })
            IconThemeComponent(isActiveTheme = uiState.activeTypeOfTheme == false,
                isSystemOrDarkTheme = uiState.isSystemOrDarkTheme,
                iconTheme = R.drawable.sun_max_fill,
                titleTheme = R.string.light_theme_title,
                activeColor = Color.Black,
                changeTheme = { actions.changeTheme(TypesTheme.LIGHT_THEME) })
            IconThemeComponent(isActiveTheme = uiState.activeTypeOfTheme == true,
                isSystemOrDarkTheme = uiState.isSystemOrDarkTheme,
                iconTheme = R.drawable.moon_fill,
                titleTheme = R.string.dark_theme_title,
                activeColor = Color.White,
                changeTheme = { actions.changeTheme(TypesTheme.DARK_THEME) })
        }
        HorizontalDivider(modifier = Modifier
            .padding(vertical=20.dp),)

        SubHeadingText(text = stringResource(R.string.icon_logo))


        FlowRow(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            uiState.listIcons.forEach { item ->
                Column(modifier = Modifier
                    .clickable {
                    actions.changeIcon(item)
                }){
                    Image(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(3.dp)
                            .border(1.dp,
                                if (item.alias == uiState.activeTypeOfIconAlias) Color(0xFF74F6A7)
                                else Color.Transparent,
                                RoundedCornerShape(size = 20.dp),
                                )
                            .padding(3.dp)
                            .border(1.dp,
                                if (item.alias == uiState.activeTypeOfIconAlias) Color.Black
                                else Color.Transparent,
                                RoundedCornerShape(size = 20.dp),
                                )
                            .clip(RoundedCornerShape(size = 20.dp))
                        )
                    Text(text = stringResource(item.titleRes),
                        modifier = Modifier.padding(horizontal = 6.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ProfileDecorScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ProfileDecorScreen(uiState = ProfileDecorUiState(), actions=object : ProfileDecorRouteListener {
                override fun onBackClick() = Unit
                override fun changeTheme(typeOfTheme: TypesTheme) = Unit
                override fun getActiveTheme() = TypesTheme.SYSTEM_THEME
                override fun changeIcon(icon: LogoIcon) = Unit
            })
        }
    }
}
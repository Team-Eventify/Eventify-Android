package feature.onboarding.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import feature.onboarding.api.OnBoardingListener
import feature.onboarding.impl.state.OnBoardingItem
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.TypographyKit
import uikit.components.TitleText
import uikit.components.buttons.PrimaryButton
import com.example.eventify.uikit.R as UiKitR


@Composable
fun OnBoardingScreen(
    actions: OnBoardingListener
) {
    val dimentions = LocalDimentions.current

    val items = listOf(
        OnBoardingItem(
            primaryImage = painterResource(UiKitR.drawable.events_svgrepo),
            title = "Актуальные мероприятия",
            body = "Все события вашего университета теперь собраны в одном удобном приложении.",
        ),
        OnBoardingItem(
            primaryImage = painterResource(UiKitR.drawable.user_add),
            title = "Легкая регистрация",
            body = "Узнавайте подробности о мероприятиях и записывайтесь на них всего в пару кликов.",
        ),
        OnBoardingItem(
            primaryImage = painterResource(UiKitR.drawable.category_svgrepo),
            title = "Интересные категории",
            body = "Выберите интересы, и приложение предложит подходящие ивенты",
        ),
        OnBoardingItem(
            primaryImage = painterResource(UiKitR.drawable.users_group),
            title = "Организуйте мероприятие",
            body = "Напишите нам и мы вместе сделаем ваше мероприятие ярким и незабываемым!",
        ),

    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimentions.windowPaddings)
    ) {
        Image(
            painter = painterResource(UiKitR.drawable.ic_launcher_foreground),
            contentDescription = stringResource(UiKitR.string.app_name),
        )
        Spacer(
            modifier = Modifier.height(10.dp),
            )
        TitleText(
            text = stringResource(UiKitR.string.app_name),
        )
        Spacer(
            modifier = Modifier.height(40.dp),
        )
        items.forEach { item ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical=10.dp)
            ) {
                Image(
                    painter = item.primaryImage,
                    contentDescription = null,
                    modifier = Modifier
                        .width(52.dp)
                        .height(52.dp))
                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
                Column {
                    Text(
                        text = item.title,
                        style = TypographyKit.bodyMedium
                    )
                    Text(
                        text=item.body,
                        style = TypographyKit.bodyRegular.copy(
                            color = Color.White.copy(alpha = 0.5f),
                        )
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        PrimaryButton(
            text = stringResource(UiKitR.string.next),
            onClick = actions::flowNext,
        )
    }
}

@Composable
@Preview(name = "OnBoarding")
private fun OnBoardingScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            OnBoardingScreen(
                actions = object : OnBoardingListener {
                    override fun flowNext() = Unit
                },
            )
        }
    }
}


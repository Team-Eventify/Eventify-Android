package feature.onboarding.impl

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.ui.auth.onboarding.state.OnBoardingItem
import com.example.eventify.presentation.ui.auth.onboarding.state.OnBoardingListener
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions


@Composable
fun OnBoardingScreen(
    actions: OnBoardingListener
) {
    val dimentions = LocalDimentions.current

    val items = listOf(
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.events_svgrepo),
            title = "Актуальные мероприятия",
            body = "Все события вашего университета теперь собраны в одном удобном приложении.",
        ),
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.user_add),
            title = "Легкая регистрация",
            body = "Узнавайте подробности о мероприятиях и записывайтесь на них всего в пару кликов.",
        ),
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.category_svgrepo),
            title = "Интересные категории",
            body = "Выберите интересы, и приложение предложит подходящие ивенты",
        ),
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.users_group),
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
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(R.string.app_name),
        )
        Spacer(
            modifier = Modifier.height(10.dp),
            )
        TitleText(
            text = stringResource(R.string.app_name),
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
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        )
                    Text(
                        text=item.body,
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        PrimaryButton(
            onClick = actions::flowNext,
        ) {
            PrimaryButtonText(
                text = stringResource(R.string.next),
            )
        }
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


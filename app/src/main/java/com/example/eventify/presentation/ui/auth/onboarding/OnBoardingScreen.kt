package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.auth.onboarding.components.OnboardingView
import com.example.eventify.presentation.ui.theme.EventifyTheme


@Composable
fun OnBoardingScreen(
    state: OnBoardingState,
    actions: OnBoardingActions
) {
    var currentPageState by remember { mutableStateOf(0) }

    val items = listOf(
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.planet),
            title = "Актуальные мероприятия",
            body = "Все события вашего университета теперь собраны в одном удобном приложении.",
            primaryButtonText = stringResource(R.string.next)
        ),
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.touch_action),
            title = "Легкая регистрация",
            body = "Узнавайте подробности о мероприятиях \n" +
                    "и записывайтесь на них всего в пару кликов.",
            primaryButtonText = stringResource(R.string.next)
        ),
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.checklist),
            title = "Интересные категории",
            body = "Выберите интересы, и приложение предложит ивенты, которые соответствуют вашим вкусам.",
            primaryButtonText = stringResource(R.string.next)
        ),
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.utils),
            title = "Организуйте мероприятие",
            body = "Напишите нам и мы вместе сделаем \n" +
                    "ваше мероприятие ярким и незабываемым!",
            primaryButtonText = stringResource(R.string.next)
        ),
        // TODO нужно импортнуть картинку из фигмы
        OnBoardingItem(
            primaryImage = painterResource(R.drawable.ic_launcher_foreground),
            title = "Живи. Учись. Совершенствуй.",
            body = null,
            primaryButtonText = stringResource(R.string.register_action)
        ),

    )

    val pagerState = rememberPagerState{
        items.size
    }

    LaunchedEffect(currentPageState) {
        pagerState.animateScrollToPage(currentPageState)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = currentPageState,
            containerColor = Color.Transparent
        ) {
            items.forEachIndexed { index, _ ->
                Tab(
                    selected = currentPageState == index,
                    onClick = { currentPageState = index },
                    text = null
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            contentPadding = PaddingValues(horizontal = 10.dp),
            pageSpacing = 20.dp,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            OnboardingView(
                itemState = items[index],
                onNext = {
                    if (index == items.size -1)
                        actions.onFinishOnboarding()
                    else
                        currentPageState = (index + 1) % items.size
                },
                onSkip = actions.onFinishOnboarding
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
                state = OnBoardingState(),
                actions = OnBoardingActions()
            )
        }
    }
}


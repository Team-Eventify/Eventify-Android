package com.example.eventify.presentation.ui.events.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.events.feedback.components.MarkHorizontalGroup
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SubHeadingText
import com.example.eventify.presentation.ui.shared.TextInput
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton
import com.example.eventify.presentation.ui.theme.EventifyTheme


@Composable
fun FeedbackScreen(
    state: FeedbackState,
    actions: FeedbackActions
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        SubHeadingText("Оцени мероприятие")
        MarkHorizontalGroup(
            options = List(5){ it to it.toString() },
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(top = 10.dp)
        )

        Spacer(Modifier.height(20.dp))

        SubHeadingText("Что тебе понравилось?")
        TextInput(
            text = state.positiveFeedbackText,
            onValueChange = actions.onChangePositiveFeedbackText,
            isError = state.isPositiveFeedbackTextError,
            singleLine = false
        )

        Spacer(Modifier.height(20.dp))

        SubHeadingText("А что не понравилось?")
        TextInput(
            text = state.negativeFeedbackText,
            onValueChange = actions.onChangeNegativeFeedbackText,
            isError = state.isPositiveFeedbackTextError,
            singleLine = false
        )

        Spacer(Modifier.height(20.dp))

        SubHeadingText("Что мы забыли спросить, но ты хотел(а) нам рассказать?")
        TextInput(
            text = state.additionalFeedbackText,
            onValueChange = actions.onChangeAdditionalFeedbackText,
            isError = state.isPositiveFeedbackTextError,
            singleLine = false

        )

        Spacer(Modifier.height(20.dp))

        PrimaryButton(
            onClick = actions.onSendForm
        ) {
            PrimaryButtonText(stringResource(R.string.send))
        }
    }
}

@Composable
@Preview(name = "Feedback", showBackground = true)
private fun FeedbackScreenPreviewDark() {
    EventifyTheme(darkTheme = true) {
        Surface {
            FeedbackScreen(
                state = FeedbackState(),
                actions = FeedbackActions()
            )
        }
    }
}

@Composable
@Preview(name = "Feedback", showBackground = true)
private fun FeedbackScreenPreviewLight() {
    EventifyTheme(darkTheme = false) {
        Surface {
            FeedbackScreen(
                state = FeedbackState(),
                actions = FeedbackActions()
            )
        }
    }
}


package com.example.eventify.presentation.ui.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.BodyText
import com.example.eventify.presentation.ui.shared.CategorySelectChip
import com.example.eventify.presentation.ui.shared.CategorySelector
import com.example.eventify.presentation.ui.shared.PrimaryButton
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.TitleText
import kotlin.random.Random


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelectScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
        ,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            TitleText(text = "Выбери интересные тебе категории!")
            Spacer(modifier = modifier.height(10.dp))
            BodyText(text = "Мы подберём рекомендации ивентов под твои вкусы.")
            Spacer(modifier = modifier.height(30.dp))
//            CategorySelector()

        }
        Column {
            AnnotationText(text = "Ты всегда сможешь изменить свой выбор в настройках.")
            PrimaryButton(
                onClick = { /*TODO*/ }
            ) {
                PrimaryButtonText(text = "Далее")
            }
            Spacer(modifier = modifier.height(20.dp))
            AnnotationText(text = "Пропустить", textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline, modifier = modifier.fillMaxWidth())

        }
    }
}

@Preview(name = "CategorySelectScreen", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCategorySelectScreen() {
    CategorySelectScreen()
}
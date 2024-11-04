package com.example.eventify.presentation.ui.shared


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelector(
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
    ){
        listOf("Наука", "Спорт", "Хакатоны", "ML", "GameDev", "Робототехника", "Студенческая жизнь", "Наставничество", "Backend", "Frontend", "Media").forEach {
            CategorySelectChip(text = it)
        }
    }
}

@Preview(name = "CategorySelector")
@Composable
private fun PreviewCategorySelector() {
    CategorySelector()
}
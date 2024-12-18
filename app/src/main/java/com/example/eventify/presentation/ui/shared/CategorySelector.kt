package com.example.eventify.presentation.ui.shared


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.models.CategorySelectItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelector(
    categories: List<CategorySelectItem>,
    onClickCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
    ){
        categories.forEach {
            CategorySelectChip(
                category = it,
                onSelect = onClickCategory
            )
        }
    }
}

@Preview(name = "CategorySelector")
@Composable
private fun PreviewCategorySelector() {
    CategorySelector(
        categories = listOf(
            CategorySelectItem(
                id = "",
                title = "Backend",
                selected = true
            ),
            CategorySelectItem(
                id = "",
                title = "Frontend",
                selected = false
            ),
            CategorySelectItem(
                id = "",
                title = "Сопрт",
                selected = false
            ),
            CategorySelectItem(
                id = "",
                title = "Наука",
                selected = false
            ),
            CategorySelectItem(
                id = "",
                title = "Game Dev",
                selected = true
            ),

        ),
        onClickCategory = {_->}
    )
}

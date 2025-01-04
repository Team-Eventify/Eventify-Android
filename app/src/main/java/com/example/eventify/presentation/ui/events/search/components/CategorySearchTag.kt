package com.example.eventify.presentation.ui.events.search.components

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.theme.EventifyTheme
import java.util.UUID

@Composable
fun CategorySearchTag(
    onClick: () -> Unit,
    categoryItem: CategorySelectItem,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = categoryItem.selected,
        onClick = onClick,
        label = {
            Text(
                text = categoryItem.title
            )
        },
        modifier = Modifier
            .composed { modifier }
    )
}

@Preview(name = "CategorySearchTag")
@Composable
private fun PreviewCategorySearchTagUnselectedDark() {
    EventifyTheme(darkTheme = true) {
        CategorySearchTag(
            categoryItem = CategorySelectItem(
                id = UUID.randomUUID().toString(),
                title = LoremIpsum(2).values.toList().joinToString(),
                selected = false
            ),
            onClick = {}
        )
    }
}

@Preview(name = "CategorySearchTag")
@Composable
private fun PreviewCategorySearchTagSelectedDark() {
    EventifyTheme(darkTheme = true) {
        CategorySearchTag(
            categoryItem = CategorySelectItem(
                id = UUID.randomUUID().toString(),
                title = LoremIpsum(2).values.toList().joinToString(),
                selected = true
            ),
            onClick = {}
        )
    }
}
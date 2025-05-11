package feature.search.impl.components

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import domain.models.CategorySelectItem
import uikit.EventifyTheme
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
                selected = false,
                color = Color.Cyan
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
                selected = true,
                color = Color.Cyan
            ),
            onClick = {}
        )
    }
}
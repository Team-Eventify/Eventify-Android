package feature.search.impl.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.models.Category
import feature.search.impl.state.CategoryId
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import core.common.extentions.toColor
import uikit.components.cards.CategoryCard
import uikit.space18

@Composable
fun CategoriesSearch(
    categories: List<Category>,
    onCategoryClick: (CategoryId) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            categories,
            key = { it.id }
        ) { category ->
            CategoryCard(
                title = category.title,
                coverUrl = category.cover,
                color = category.color.toColor(MaterialTheme.colorScheme.primary),
            ) {
                onCategoryClick(category.id)
            }
            Spacer(Modifier.height(space18))
        }
    }
}

package feature.profileEdit.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.models.CategorySelectItem
import uikit.components.CategorySelectChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelector(
    categories: List<CategorySelectItem>,
    onClickCategory: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = Modifier
            .defaultMinSize(minHeight = 30.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
    ){
        categories.forEach {
            CategorySelectChip(
                title = it.title,
                isSelected = it.selected,
                color = it.color,
                onSelect = {
                    onClickCategory(it.id, !it.selected)
               },
            )
        }
    }
}
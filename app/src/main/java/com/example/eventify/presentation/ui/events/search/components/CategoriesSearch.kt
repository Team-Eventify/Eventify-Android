package com.example.eventify.presentation.ui.events.search.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eventify.domain.models.Category
import com.example.eventify.presentation.ui.common.CategoryCard
import com.example.eventify.presentation.ui.events.search.state.CategoryId
import com.example.eventify.presentation.ui.theme.space18

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
                category = category,
                onClick = onCategoryClick,
            )
            Spacer(Modifier.height(space18))
        }
    }
}

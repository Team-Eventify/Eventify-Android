package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.domain.models.Category

@Composable
fun CategoryCard(
    category: Category,
    onClick: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF93B3E6)
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick?.invoke(category.id)
            }
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = category.title,
                fontSize = 24.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.eventify_coaches_cuate),
                contentDescription = "title"
            )
        }
    }
}

@Preview(name = "CategoryCard", showBackground = true)
@Composable
private fun PreviewCategoryCard() {
    CategoryCard(
        category = Category(
            id = "",
            title = "Backend"
        ),
        onClick = {}
    )
}
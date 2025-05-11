package uikit.components

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
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import uikit.LocaleImageLoader

@Composable
fun CategoryCard(
    category: Category,
    onClick: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val imageLoader = LocaleImageLoader.current

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = category.color.toColor(Color.Cyan)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick?.invoke(category.id)
            }
            .composed { modifier }
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
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://eventify.website/api/v1/files/${category.cover}")
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = "cover",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth(.6f)
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
            title = "Backend",
            color = "FF93B3E6",
            cover = ""
        ),
        onClick = {}
    )
}
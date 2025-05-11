package uikit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import uikit.LocaleImageLoader
import uikit.utils.conditional

@Composable
fun CategoryCard(
    title: String,
    coverUrl: String,
    color: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val imageLoader = LocaleImageLoader.current

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .conditional(onClick != null) {
                clickable {
                    onClick?.invoke()
                }
            }
            .composed { modifier }
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://eventify.website/api/v1/files/$coverUrl")
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

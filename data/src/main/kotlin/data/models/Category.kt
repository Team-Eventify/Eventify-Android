package data.models

import data.remote.models.category.CategoryInfoResponse

data class Category(
    val id: String,
    val title: String,
    val color: String,
    val cover: String
)

fun CategoryInfoResponse.toBusiness(): Category = Category(
    id = this.id,
    title = this.title,
    color = this.color,
    cover = this.cover,
)


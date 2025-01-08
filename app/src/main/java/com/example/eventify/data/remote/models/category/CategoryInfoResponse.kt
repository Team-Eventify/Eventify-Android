package com.example.eventify.data.remote.models.category

import com.example.eventify.domain.models.Category

data class CategoryInfoResponse(
    val id: String,
    val title: String,
    val color: String,
    val cover: String
){
    fun toCategoryInfo() = Category(
        id = id,
        title = title,
        color = color,
        cover = cover
    )
}
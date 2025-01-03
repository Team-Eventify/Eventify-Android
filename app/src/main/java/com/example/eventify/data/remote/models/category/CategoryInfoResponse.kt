package com.example.eventify.data.remote.models.category

import com.example.eventify.domain.models.Category

data class CategoryInfoResponse(
    val id: String,
    val title: String
){
    fun toCategoryInfo() = Category(
        id = id,
        title = title
    )
}
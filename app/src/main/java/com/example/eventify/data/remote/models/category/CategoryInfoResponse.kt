package com.example.eventify.data.remote.models.category

import com.example.eventify.data.models.CategoryInfo

data class CategoryInfoResponse(
    val id: String,
    val title: String
){
    fun toCategoryInfo() = CategoryInfo(
        id = id,
        title = title
    )
}
package com.example.eventify.domain.models

import java.time.LocalDateTime
import java.time.ZoneOffset

typealias PictureUUID = String
typealias CategoryUUID = String

data class EventDetail(
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Long,
    val id: String,
    val location: String,
    val organizationID: String,
    val pictures: List<PictureUUID>,
    val categories: List<CategoryUUID>? = null,
    val start: Long,
    val state: String,
    val subscribed: Boolean,
    val title: String
) {
    val isFinished: Boolean
        get() {
            return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() > end
        }
}


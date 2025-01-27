package com.example.eventify.data.remote.models.events

import com.example.eventify.domain.models.EventDetail

data class EventDetailResponse(
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Int,
    val id: String,
    val location: String,
    val organizationID: String,
    val pictures: List<String>,
    val categories: List<String>? = null,
    val start: Int,
    val state: String,
    val subscribed: Boolean,
    val title: String
)

fun EventDetailResponse.toEventDetail() = EventDetail(
    capacity = this.capacity ,
    cover = this.cover ,
    description = this.description ,
    end = this.end ,
    id = this.id ,
    location = this.location ,
    organizationID = this.organizationID ,
    pictures = this.pictures.map { "https://eventify.website/api/v1/files/$it" },
    start = this.start ,
    state = this.state ,
    subscribed = this.subscribed ,
    title = this.title,
    categories = this.categories
)
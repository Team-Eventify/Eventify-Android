package com.example.eventify.data.remote.models.events


data class EventsFilterData(
    val limit: Int? = null,
    val offset: Int? = null,
    val ownerId: String? = null,
    val start: Int? = null,
    val end: Int? = null,
    val categoryIds: List<String>? = null
){
    /**
     * Returns raw value that can be passed to api query call
     * @return Joined string of categoryIds separated by comma
     */
    val validCategoryIds: String?
        get() = categoryIds?.joinToString(",")
}

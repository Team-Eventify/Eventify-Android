package com.example.eventify.presentation.ui.events.search.state

typealias EventId = String
typealias CategoryId = String

interface SearchListener {
    fun changeSearchMode(mode: SearchMode)
    fun onEventClick(eventId: EventId)
    fun onCategoryClick(categoryId: CategoryId)
    fun onChangeSearchQuery(value: String)
    fun search()
    fun cleanSearch()
}
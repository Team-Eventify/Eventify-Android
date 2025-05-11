package feature.searchResult.impl.state


interface SearchDetailListener {
    fun onBackClick()
    fun onEventClick(eventId: String)
}
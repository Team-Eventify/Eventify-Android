package feature.search.impl.state


typealias EventId = String
typealias CategoryId = String

interface SearchListener {
    fun changeSearchMode(mode: SearchMode)
    fun onEventClick(eventId: String)
    fun onCategoryClick(categoryId: String)
    fun onChangeSearchQuery(value: String)
    fun search()
    fun cleanSearch()
    fun refresh()
}
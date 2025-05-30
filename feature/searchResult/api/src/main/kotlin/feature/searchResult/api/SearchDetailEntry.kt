package feature.searchResult.api

import core.common.navigation.ARG_CATEGORY_ID
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry
import core.common.navigation.EventsRoot

val SearchDetailPath = EventsRoot.child(
    "search-detail",
    ARG_CATEGORY_ID,
)



interface SearchDetailEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = SearchDetailPath

}


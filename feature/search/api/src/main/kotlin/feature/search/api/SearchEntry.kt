package feature.search.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry
import core.common.navigation.EventsRoot

val SearchPath = EventsRoot.child("search")

interface SearchEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = SearchPath
}


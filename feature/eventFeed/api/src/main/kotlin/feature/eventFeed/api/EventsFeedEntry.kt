package feature.eventFeed.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry
import core.common.navigation.EventsRoot


val EventFeedPath = EventsRoot.child("feed")


interface EventsFeedEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventFeedPath
}


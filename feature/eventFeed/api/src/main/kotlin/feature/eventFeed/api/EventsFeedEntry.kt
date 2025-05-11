package feature.eventFeed.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val EventsRootPath = BaseDestination("events")

val EventFeedPath = EventsRootPath.child("feed")


interface EventsFeedEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventFeedPath
}


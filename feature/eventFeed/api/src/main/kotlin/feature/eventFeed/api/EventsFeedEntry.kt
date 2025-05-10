package feature.eventFeed.api


val EventsRootPath = BaseDestination("events")

val EventFeedPath = EventsRootPath.child("feed")


interface EventsFeedEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventFeedPath
}


package feature.eventDetail.api

import core.common.navigation.ARG_EVENT_ID
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val EventDetailPath = EventsRootPath.child("{$ARG_EVENT_ID}/detail")

interface EventDetailEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventDetailPath
}


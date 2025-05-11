package feature.myEvents.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val MyEventsPath = EventsRootPath.child("my-events")

interface MyEventsEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = MyEventsPath
}


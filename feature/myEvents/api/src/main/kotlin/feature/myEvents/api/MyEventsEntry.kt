package feature.myEvents.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry
import core.common.navigation.EventsRoot


val MyEventsPath = EventsRoot.child("my-events")

interface MyEventsEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = MyEventsPath
}


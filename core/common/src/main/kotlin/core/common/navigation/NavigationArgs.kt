package core.common.navigation

const val ARG_EVENT_ID = "event_id"
const val ARG_CATEGORY_ID = "event_id"
const val ARG_SHARED_EMAIL = "shared_email"

const val ARG_SEARCH_MODE = "search_mode"
const val ARG_SEARCH_TEXT = "search_text"

object RootRoute : BaseDestination("")

object EventsRoot : BaseDestination("events")

object AccountRoot : BaseDestination("account")

object AuthRoot : BaseDestination("auth")
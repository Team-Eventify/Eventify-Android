package feature.eventDetail.api

interface EventDetailListener {
    fun navigateUp(): Unit
    fun onActionClick(): Unit
    fun goToRatePage(): Unit
}
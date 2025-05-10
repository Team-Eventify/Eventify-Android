package feature.profile.api

interface ProfileListener {
    fun onLogOut(): Unit
    fun navigateToProfileEdit(): Unit
    fun navigateToAppInfo(): Unit
}
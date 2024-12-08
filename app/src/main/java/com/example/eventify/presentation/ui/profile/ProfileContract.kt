package com.example.eventify.presentation.ui.profile

import com.example.eventify.presentation.models.UserShortInfo


/**
 * UI State that represents ProfileScreen
 **/
data class ProfileState(
    val userInfo: UserShortInfo?
){
    companion object{
        fun default() = ProfileState(
            userInfo = null
        )
    }
}

/**
 * Profile Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfileActions(
    val onLogOut: () -> Unit,
    val navigateToProfileEdit: () -> Unit,
    val onDeleteAccount: () -> Unit,
){
    companion object{
        fun default() = ProfileActions(
            onLogOut = {},
            navigateToProfileEdit = {},
            onDeleteAccount = {}
        )
    }
}
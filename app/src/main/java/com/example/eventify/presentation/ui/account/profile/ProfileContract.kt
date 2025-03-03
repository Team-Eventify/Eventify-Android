package com.example.eventify.presentation.ui.account.profile

import com.example.eventify.presentation.models.UserShortInfo


/**
 * UI State that represents ProfileScreen
 **/
data class ProfileState(
    val isLoading: Boolean = false,
    val userInfo: UserShortInfo?,
){
    companion object{
        fun default() = ProfileState(
            userInfo = null
        )
    }
}

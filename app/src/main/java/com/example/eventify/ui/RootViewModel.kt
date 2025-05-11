package com.example.eventify.ui

import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.auth.LogOutUseCase
import javax.inject.Inject


@HiltViewModel
class RootViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
) : BaseViewModel() {
    val authState by lazy { authProvider.authorizationState }

    fun logOut() {
        launchCatching {
            logOutUseCase()
        }
    }
}
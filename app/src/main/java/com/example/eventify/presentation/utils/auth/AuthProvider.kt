package com.example.eventify.presentation.utils.auth

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthProvider @Inject constructor() {
    private val mutableAuthorizationState = MutableSharedFlow<AuthorizeType?>(1)

    val authorizationState: SharedFlow<AuthorizeType?> = mutableAuthorizationState.asSharedFlow()

    suspend fun updateState(type: AuthorizeType) {
        mutableAuthorizationState.emit(type)
    }
}
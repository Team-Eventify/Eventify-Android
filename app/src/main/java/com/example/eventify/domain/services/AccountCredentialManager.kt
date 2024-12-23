package com.example.eventify.domain.services

import android.app.Activity
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager

class AccountCredentialManager(
    private val activity: Activity
){
    private val credentialManager = CredentialManager.create(activity)

    suspend fun signUp(login: String, password: String) {
        credentialManager.createCredential(
            context = activity,
            request = CreatePasswordRequest(
                id = login,
                password = password
            )
        )
    }
}
package com.example.eventify.domain.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


class FirebaseNotificationService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        // Respond to received message
        Timber.d("<onMessageReceived ${message.notification?.title}>")
    }

    override fun onNewToken(token: String) {
        // Update token on server
        Timber.d("onNewToken: $token")
    }
}


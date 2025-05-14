package com.example.eventify.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.core.app.NotificationCompat
import com.example.eventify.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random
import ui.MainActivity
import io.appmetrica.analytics.push.provider.firebase.AppMetricaMessagingService
import com.example.eventify.uikit.R as UiKitR


class FirebaseNotificationService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        if (AppMetricaMessagingService.isNotificationRelatedToSDK(message)) {
            AppMetricaMessagingService().processPush(this, message)
            return
        }

        message.notification?.let { notification ->
            sendNotification(notification)
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channelId = getString(R.string.firebase_notification_channel_id)
        val channel = NotificationChannel(
            channelId,
            "App Notifications",
            NotificationManager.IMPORTANCE_HIGH // ВАЖНО: High для всплывающих уведомлений
        ).apply {
            enableVibration(true)
            enableLights(true)
            setShowBadge(true)
        }

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    override fun onNewToken(token: String) {
        // Update token on server
        super.onNewToken(token)
        AppMetricaMessagingService().processToken(this, token)
    }

    private fun sendNotification(message: RemoteMessage.Notification) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, FLAG_IMMUTABLE
        )

        val channelId = this.getString(R.string.firebase_notification_channel_id)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(message.title)
            .setContentText(message.body)
            .setSmallIcon(UiKitR.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        manager.notify(Random.nextInt(), notificationBuilder.build())
    }
}

















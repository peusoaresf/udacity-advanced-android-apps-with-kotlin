package com.example.android.eggtimernotifications

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

val TAG = "MyFirebaseMessagingService"

class MyFirebaseMessagingService: FirebaseMessagingService() {

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "Message notification body: ${it.body}")

            it.body?.let { body -> sendNotification(body) }
        }
    }

    @SuppressLint("LongLogTag")
    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: ${token}")

//        sendRegistrationToServer(token)
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }
}
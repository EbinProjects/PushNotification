package com.example.pushnotification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotifications : FirebaseMessagingService() {

  /*  Ensures Device Can Receive Notifications: Without the current FCM token, the device will not receive push notifications.
    Token Management: Since tokens can expire or change, this method is critical for keeping the backend up-to-date with the latest token.*/

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}
package com.example.pushnotification

data class SendmessgingDto(
    val token : String?,
    val notificationBody : NotificationBody
)

data class NotificationBody(
    val title : String ="",
    val body : String =""
)
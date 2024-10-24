package com.example.pushnotification

data class ChateState(
    val isEnteringToken : Boolean = true,
    val remoteToken : String ="",
    val messagingTxt : String = ""
)

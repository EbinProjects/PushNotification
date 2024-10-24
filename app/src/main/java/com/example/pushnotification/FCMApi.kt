package com.example.pushnotification

import retrofit2.http.Body
import retrofit2.http.POST

interface FCMApi {

    @POST("/send")
    suspend fun SendMsg(@Body req :SendmessgingDto)

    @POST("/broadcast")
    suspend fun SendBroadCast(@Body req :SendmessgingDto)
}
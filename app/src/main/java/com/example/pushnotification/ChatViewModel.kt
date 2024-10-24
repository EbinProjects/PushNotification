package com.example.pushnotification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ChatViewModel : ViewModel() {
   var state by mutableStateOf(ChateState())  /*The by keyword is used for delegation. Instead of storing the value directly in the state variable, it is delegated to mutableStateOf, which manages the state internally and handles changes in a way that Jetpack Compose can react to.*/
       private set /* This means that while the state variable can be read from outside the class (it is var, so it's mutable), it can only be modified (set) from within the class.*/
    private val fcmApi: FCMApi = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(MoshiConverterFactory.create()).build().create(FCMApi::class.java)

    init {
        viewModelScope.launch {
            Firebase.messaging.subscribeToTopic("chat").await()
        }
    }

    fun onRempteTokenChange(token : String){
        state = state.copy(
            remoteToken = token
        )
    }

    fun submitRemoteToken(){
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun onMessageChange(msg : String){
        state = state.copy(
            messagingTxt = msg
        )
    }

    fun sendMessage(isBroadcst : Boolean){
        viewModelScope.launch {
            val mssageDto = SendmessgingDto(
                token = if (isBroadcst) null else state.remoteToken,
                NotificationBody(
                    title = "New Message",
                    body = state.messagingTxt
                )
            )
        }
    }
}
package com.example.pushnotification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ChatScreen(
    message : String,
    onMessage : (String) -> Unit,
    onMessageSend : () -> Unit,
    onMessageBroadCast : () -> Unit
){
  Column(modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {

      OutlinedTextField(
          value = message,
          onValueChange = onMessage,
          placeholder = {
              Text("Enter Message")
          },
          modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
      )
      Spacer(Modifier.padding(5.dp))
      Row(modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End) {
          IconButton(
              onClick = onMessageSend
          ) {
              Icon(imageVector = Icons.Default.Send,contentDescription = null)
          }
          Spacer(Modifier.padding(5.dp))
          IconButton(
              onClick = onMessageBroadCast
          ) {
              Icon(imageVector = Icons.Default.Share,contentDescription = null)
          }
      }
  }
}
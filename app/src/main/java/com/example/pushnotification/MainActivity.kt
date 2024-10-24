package com.example.pushnotification

import OnTokenDialouge
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pushnotification.ui.theme.PushNotificationTheme

class MainActivity : ComponentActivity() {
    val viewModel : ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestNotificattionPermission()
        setContent {
            PushNotificationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   val state =viewModel.state
                    if (state.isEnteringToken){
                        OnTokenDialouge(
                            token = state.remoteToken,
                            onSubMit = viewModel::submitRemoteToken,
                            onTokenChange = viewModel::onRempteTokenChange
                        )
                    }else{
                        ChatScreen(
                            message = state.messagingTxt,
                            onMessage = viewModel::onMessageChange,
                            onMessageSend ={
                                viewModel.sendMessage(isBroadcst = false)
                            } ,
                            onMessageBroadCast ={
                                viewModel.sendMessage(isBroadcst = true)

                            }
                        )
                    }
                }
            }
        }
    }

    private fun requestNotificattionPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
  val hasPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)== PackageManager.PERMISSION_GRANTED
      if (!hasPermission){
  ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),10)
      }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PushNotificationTheme {
        Greeting("Android")
    }
}
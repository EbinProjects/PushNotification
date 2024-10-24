import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun OnTokenDialouge(
    token : String,
    onTokenChange : (String) -> Unit,
    onSubMit :() -> Unit
){
  val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    var scope = rememberCoroutineScope()
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth().clip(
            RoundedCornerShape(15.dp)
        ).background(MaterialTheme.colorScheme.surface).padding(16.dp)) {
           OutlinedTextField(
               value = token,
               onValueChange = onTokenChange,
               modifier = Modifier.fillMaxWidth(),
               placeholder = {
                   Text("Enter Token")
               },
               maxLines = 1
           )
            Spacer(Modifier.padding(10.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
            OutlinedButton(
                onClick = {
                    scope.launch {
                       val tokenQ = Firebase.messaging.token.await()
                        clipboardManager.setText(annotatedString = AnnotatedString(tokenQ))
                        Toast.makeText(context,"Token copied",Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
              Text("On Copy")
            }
                Spacer(Modifier.padding(5.dp))
                Button(onClick = onSubMit) {
                    Text("Submit")
                }
            }
        }
    }

}
package tech.dagimtesfaye.cbe_balance_tracker.view.profileSetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tech.dagimtesfaye.cbe_balance_tracker.data.repository.SharedPreferencesManager
import tech.dagimtesfaye.cbe_balance_tracker.view.components.OtpTextField

@Composable
fun ProfilePinSetupScreen(
    navController: NavController, viewModel: ProfileSetupViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context = context)
    val pin by viewModel.pin.observeAsState("")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Almost Done!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Just need you to set a passcode.",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        OtpTextField(
            otpText = pin,
            onOtpTextChange = { value, otpInputFilled ->
                viewModel.onPinChange(value)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.onPinSetScreenNextClicked(context, navController)
            },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            enabled = pin.length == 4
        ) {
            Text("Finish")
        }
    }
}
package tech.dagimtesfaye.cbe_balance_tracker.view.login

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
fun LoginScreen(
    navController: NavController, viewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context = context)
    val name = sharedPreferencesManager.getName()
    val pin by viewModel.pin.observeAsState("")
    val errorText by viewModel.errorText.observeAsState("")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back $name!",
            style = MaterialTheme.typography.headlineSmall,
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
        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.authenticate(context, navController)
            },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            enabled = pin.length == 4
        ) {
            Text(
                "Login",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
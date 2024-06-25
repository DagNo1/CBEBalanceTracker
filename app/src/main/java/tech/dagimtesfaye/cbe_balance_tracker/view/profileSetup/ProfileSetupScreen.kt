package tech.dagimtesfaye.cbe_balance_tracker.view.profileSetup

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileSetupScreen(
    navController: NavController,
    viewModel: ProfileSetupViewModel = viewModel()
) {
    val readSmsPermission = rememberPermissionState(
        Manifest.permission.READ_SMS
    )
    val name by viewModel.name.observeAsState("")
    val smsPermissionGranted by viewModel.smsPermissionGranted.observeAsState(false)
    val termsAccepted by viewModel.termsAccepted.observeAsState(false)

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome to CBE Balance Tracker!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.onNameChanged(it) },
            label = { Text("Enter your name") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { keyboardController?.hide() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // SMS permission checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = smsPermissionGranted || readSmsPermission.status.isGranted,
                onCheckedChange = {
                    if (!readSmsPermission.status.isGranted) {
                        readSmsPermission.launchPermissionRequest()
                    } else {
                        viewModel.onSmsPermissionChecked(it)
                    }
                }
            )
            Text(
                text = "Grant permission to read SMS",
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Terms and conditions checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = { viewModel.onTermsChecked(it) }
            )
            Text(
                text = "I agree to the Terms and Conditions",
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Next button
        Button(
            onClick = { viewModel.onNextClicked(context, navController) },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            enabled = name.isNotBlank() && readSmsPermission.status.isGranted && termsAccepted
        ) {
            Text("Next")
        }
    }
}

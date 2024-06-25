package tech.dagimtesfaye.cbe_balance_tracker.view.termsAndAgreements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun TermsAndAgreementsScreen(onPermissionGranted: () -> Unit) {
    TermsAndAgreements(onPermissionGranted = onPermissionGranted)
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun TermsAndAgreements(onPermissionGranted: () -> Unit) {
    val readSmsPermission = rememberPermissionState(
        android.Manifest.permission.READ_SMS
    )
    if (readSmsPermission.status.isGranted) {
        onPermissionGranted()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 20.dp),
                text = "Permission required for this application to work. " +
                        "Please grant the sms read permission",
                textAlign = TextAlign.Center
            )
            Button(onClick = { readSmsPermission.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }
}
package tech.dagimtesfaye.cbe_balance_tracker.view.profileSetup

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.isGranted
import tech.dagimtesfaye.cbe_balance_tracker.data.repository.SharedPreferencesManager

@Composable
fun ProfilePinSetupScreen(viewModel: ProfileSetupViewModel = viewModel()) {
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context = context)
    val name = sharedPreferencesManager.getName()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Hey there $name",
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
        // Next button
        Button(
            onClick = { },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            enabled = true
        ) {
            Text("Next")
        }
    }
}
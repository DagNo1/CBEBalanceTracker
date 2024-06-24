package tech.dagimtesfaye.cbe_balance_tracker.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import tech.dagimtesfaye.cbe_balance_tracker.data.model.SmsData


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val smsList by viewModel.smsList.observeAsState(emptyList())
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getSms(context)
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Box (
            co
        ){

        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
                items(smsList) { sms ->
                    SmsItem(sms)
                }}
    }
}

@Composable
fun SmsItem(sms: SmsData) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Date: ${sms.date}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Type: ${sms.debitOrCredit}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Amount: ${sms.amount}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Balance: ${sms.remainingBalance}", style = MaterialTheme.typography.bodyMedium)
        sms.receiptLink?.let { link ->
            Text(text = "Receipt: $link", style = MaterialTheme.typography.bodyMedium)
        }
//        Spacer(modifier = Modifier.height(8.dp))
    }
}
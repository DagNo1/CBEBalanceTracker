package tech.dagimtesfaye.cbe_balance_tracker.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.dagimtesfaye.cbe_balance_tracker.view.components.LineGraph
import tech.dagimtesfaye.cbe_balance_tracker.view.components.SmsItem


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val smsList by viewModel.smsList.observeAsState(emptyList())
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getSms(context)
    }
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        if (smsList.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
            )  {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Balance",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        smsList.first().remainingBalance + " BR.",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            LineGraph(smsDataList = smsList)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(smsList) { sms ->
                    SmsItem(sms)
                }
            }
        } else {
            Text("No SMS data available", style = MaterialTheme.typography.bodyLarge)
        }

    }
}

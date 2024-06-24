package tech.dagimtesfaye.cbe_balance_tracker.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.dagimtesfaye.cbe_balance_tracker.view.components.SmsItem


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val smsList by viewModel.smsList.observeAsState(emptyList())
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getSms(context)
    }
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(smsList) { sms ->
                SmsItem(sms)
            }
        }
    }
}

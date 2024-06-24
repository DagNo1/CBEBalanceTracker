package tech.dagimtesfaye.cbe_balance_tracker.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.dagimtesfaye.cbe_balance_tracker.R
import tech.dagimtesfaye.cbe_balance_tracker.data.model.SmsData
import tech.dagimtesfaye.cbe_balance_tracker.data.model.TransactionType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun SmsItem(sms: SmsData) {
    Card(
        modifier = Modifier
            .padding(top = 7.dp, bottom = 7.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = if (sms.transactionType == TransactionType.CREDIT) R.drawable.icons_increase else R.drawable.icons_decrease),
                contentDescription = if (sms.transactionType == TransactionType.CREDIT) "Plus" else "Minus",
                modifier = Modifier
                    .size(35.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                text = sms.amount,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f)
            )
            Text(
                text = convertLongToDateString(sms.date),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f),
            )
        }
    }
}

private fun convertLongToDateString(dateLong: Long): String {
    val date = Date(dateLong)
    val format = SimpleDateFormat("MMM dd, yyyy h:mma", Locale.getDefault())
    return format.format(date).replace("AM", "am").replace("PM", "pm")
}


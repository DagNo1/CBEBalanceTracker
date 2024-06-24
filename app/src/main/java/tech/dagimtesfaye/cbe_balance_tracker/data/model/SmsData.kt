// SmsData.kt
package tech.dagimtesfaye.cbe_balance_tracker.data.model

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import java.util.Locale

data class SmsData(
    val date: String,
    val debitOrCredit: String,
    val amount: String,
    val remainingBalance: String,
    val receiptLink: String?
) {
    companion object {
        fun fromSms(body: String, date: String): SmsData? {
            Log.d("From SMS", "Raw SMS : $body")

            val balanceRegex = "Your Current Balance is ETB (\\d+(?:,\\d{3})*(?:\\.\\d{1,2})?)".toRegex()
            val amountRegex = "with ETB (\\d+(,\\d{3})*(\\.\\d{1,2})?)".toRegex()
            val linkRegex = "(https://apps\\.cbe\\.com\\.et:\\d+/\\?id=[A-Z0-9]+)".toRegex()

            val balanceMatch = balanceRegex.find(body)
            val creditMatch = body.toLowerCase(Locale.ROOT).contains("credited")
            val debitMatch = body.contains("debited")
            val amountMatch = amountRegex.find(body)
            val linkMatch = linkRegex.find(body)

            val remainingBalance = balanceMatch?.groups?.get(1)?.value?.replace(",", "")
            val debitOrCredit = when {
                creditMatch -> "credit"
                debitMatch -> "debit"
                else -> null
            }
            val receiptLink = linkMatch?.value
            Log.d("From Sms", amountMatch.toString());
            val amount = amountMatch?.groups?.get(1)?.value?.replace(",", "")

            return if (remainingBalance != null && amount != null && debitOrCredit != null) {
                SmsData(
                    date = date,
                    debitOrCredit = debitOrCredit,
                    amount = amount,
                    remainingBalance = remainingBalance,
                    receiptLink = receiptLink
                )
            } else {
                Log.e("From SMS", "Data Not Parsed: RB=$remainingBalance, AM=$amount, DC=$debitOrCredit")
                null
            }
        }

    }
}

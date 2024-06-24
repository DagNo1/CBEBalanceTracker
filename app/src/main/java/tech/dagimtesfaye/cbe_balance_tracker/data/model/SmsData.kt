// SmsData.kt
package tech.dagimtesfaye.cbe_balance_tracker.data.model

data class SmsData(
    val date: String,
    val debitOrCredit: String,
    val amount: String,
    val remainingBalance: String,
    val receiptLink: String?
) {
    companion object {
        fun fromSms(body: String, date: String): SmsData? {
            val balanceRegex = "Current Balance is ETB ([\\d,]+\\.\\d{2})".toRegex()
            val creditRegex = "has been Credited with ETB ([\\d,]+\\.\\d{2})".toRegex()
            val debitRegex = "has been debited with ETB ([\\d,]+\\.\\d{2})".toRegex()
            val linkRegex = "(https?://\\S+)".toRegex()

            val balanceMatch = balanceRegex.find(body)
            val creditMatch = creditRegex.find(body)
            val debitMatch = debitRegex.find(body)
            val linkMatch = linkRegex.find(body)

            val remainingBalance = balanceMatch?.groups?.get(1)?.value?.replace(",", "")
            val amount = creditMatch?.groups?.get(1)?.value?.replace(",", "")
                ?: debitMatch?.groups?.get(1)?.value?.replace(",", "")
            val debitOrCredit = if (creditMatch != null) "credit" else if (debitMatch != null) "debit" else null
            val receiptLink = linkMatch?.value

            return if (remainingBalance != null && amount != null && debitOrCredit != null) {
                SmsData(
                    date = date,
                    debitOrCredit = debitOrCredit,
                    amount = amount,
                    remainingBalance = remainingBalance,
                    receiptLink = receiptLink
                )
            } else {
                null
            }
        }
    }
}

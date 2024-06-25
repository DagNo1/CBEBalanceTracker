package tech.dagimtesfaye.cbe_balance_tracker.data.repository

import android.content.Context
import android.net.Uri
import android.provider.Telephony
import tech.dagimtesfaye.cbe_balance_tracker.data.model.SmsData

class SmsRepository {


    /**
     * Fetches SMS messages from the device inbox for a specific sender ("CBE").
     *
     * @param context The context of the application or activity.
     * @return A list of [SmsData] objects parsed from SMS messages.
     */
    fun fetchSms(context: Context): List<SmsData> {
        val smsList = mutableListOf<SmsData>()

        // Define the URI and projection for querying SMS messages.
        val uri: Uri = Telephony.Sms.Inbox.CONTENT_URI
        val projection = arrayOf(
            Telephony.Sms.Inbox.DATE,
            Telephony.Sms.Inbox.BODY,
            Telephony.Sms.Inbox.ADDRESS
        )

        // Specify the selection criteria to filter SMS messages from "CBE".
        val selection = "${Telephony.Sms.Inbox.ADDRESS} = ?"
        val selectionArgs = arrayOf("CBE")

        // Perform the query to retrieve SMS messages from the device inbox.
        val cursor = context.contentResolver.query(
            uri, projection, selection, selectionArgs, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
        )

        // Use the cursor in a safe manner with Kotlin's `use` function.
        cursor?.use {
            // Retrieve column indices for date and body of SMS messages.
            val dateColumn = it.getColumnIndexOrThrow(Telephony.Sms.Inbox.DATE)
            val bodyColumn = it.getColumnIndexOrThrow(Telephony.Sms.Inbox.BODY)

            // Iterate through each SMS message retrieved from the cursor.
            while (it.moveToNext()) {
                val date = it.getLong(dateColumn) // Extract the date of the SMS message.
                val body = it.getString(bodyColumn) // Extract the body (content) of the SMS message.

                // Parse SMS data from body and date using a companion object function in SmsData.
                val smsData = SmsData.fromSms(body, date)

                // Check if parsing was successful and add SmsData object to the list.
                if (smsData != null) {
                    smsList.add(smsData)
                    // Log.d("SmsRepository", "SMS Data parsed: $smsData")
                }
            }
        }

        // Log.d("SmsRepository", "Fetch complete. Total SMS count: ${smsList.size}")

        return smsList
    }

}
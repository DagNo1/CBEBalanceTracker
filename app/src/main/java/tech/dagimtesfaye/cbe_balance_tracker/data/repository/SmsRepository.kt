package tech.dagimtesfaye.cbe_balance_tracker.data.repository

import android.content.Context
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import tech.dagimtesfaye.cbe_balance_tracker.data.model.SmsData

class SmsRepository {


    fun fetchSms(context: Context): List<SmsData> {
        val smsList = mutableListOf<SmsData>()
        val uri: Uri = Telephony.Sms.Inbox.CONTENT_URI
        val projection = arrayOf(Telephony.Sms.Inbox.DATE, Telephony.Sms.Inbox.BODY, Telephony.Sms.Inbox.ADDRESS)
        val selection = "${Telephony.Sms.Inbox.ADDRESS} = ?"
        val selectionArgs = arrayOf("CBE")
        Log.d("SmsRepository", "Fetching SMS messages...")

        val cursor = context.contentResolver.query(
            uri, projection, selection, selectionArgs, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER
        )

        cursor?.use {
            val dateColumn = it.getColumnIndexOrThrow(Telephony.Sms.Inbox.DATE)
            val bodyColumn = it.getColumnIndexOrThrow(Telephony.Sms.Inbox.BODY)

            while (it.moveToNext()) {
                val date = it.getLong(dateColumn)
                val body = it.getString(bodyColumn)
                val smsData = SmsData.fromSms(body, date)
                if (smsData != null) {
                    smsList.add(smsData)
                    Log.d("SmsRepository", "SMS Data parsed: $smsData")
                }
            }
        }

        Log.d("SmsRepository", "Fetch complete. Total SMS count: ${smsList.size}")
        return smsList
    }

}
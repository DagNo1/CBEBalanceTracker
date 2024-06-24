package tech.dagimtesfaye.cbe_balance_tracker.view.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.dagimtesfaye.cbe_balance_tracker.data.model.SmsData
import tech.dagimtesfaye.cbe_balance_tracker.data.repository.SmsRepository

class HomeViewModel : ViewModel() {
    private val repository = SmsRepository()

    private val _smsList = MutableLiveData<List<SmsData>>()
    val smsList: LiveData<List<SmsData>> = _smsList

    fun getSms(context: Context) {
        viewModelScope.launch {
            try {
                // Log.d("HomeViewModel", "Fetching SMS messages from repository...")
                _smsList.value = repository.fetchSms(context)
                // Log.d("HomeViewModel", "Fetch successful.")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching SMS", e)
                // Handle error
            }
        }
    }


}


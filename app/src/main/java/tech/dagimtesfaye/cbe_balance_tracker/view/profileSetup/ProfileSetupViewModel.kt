package tech.dagimtesfaye.cbe_balance_tracker.view.profileSetup

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import tech.dagimtesfaye.cbe_balance_tracker.data.repository.SharedPreferencesManager
import tech.dagimtesfaye.cbe_balance_tracker.navigation.Screen

class ProfileSetupViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> get() = _name

    private val _smsPermissionGranted = MutableLiveData(false)
    val smsPermissionGranted: LiveData<Boolean> get() = _smsPermissionGranted

    private val _termsAccepted = MutableLiveData(false)
    val termsAccepted: LiveData<Boolean> get() = _termsAccepted

    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun onSmsPermissionChecked(checked: Boolean) {
        _smsPermissionGranted.value = checked
    }

    fun onTermsChecked(checked: Boolean) {
        _termsAccepted.value = checked
    }

    fun onNextClicked(context: Context, navController: NavController) {
        val sharedPreferencesManager = SharedPreferencesManager(context = context)
        sharedPreferencesManager.saveName(_name.value!!)
        navController.navigate(Screen.ProfilePinSetupScreen.route)
    }
}


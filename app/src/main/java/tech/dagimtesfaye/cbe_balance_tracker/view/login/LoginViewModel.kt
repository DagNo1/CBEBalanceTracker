package tech.dagimtesfaye.cbe_balance_tracker.view.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import tech.dagimtesfaye.cbe_balance_tracker.data.repository.SharedPreferencesManager
import tech.dagimtesfaye.cbe_balance_tracker.navigation.Screen

class LoginViewModel : ViewModel() {
    private val _pin = MutableLiveData("")
    val pin: LiveData<String> get() = _pin
    fun onPinChange(newPin: String) {
        _pin.value = newPin
    }

    private val _errorText = MutableLiveData("")
    val errorText: LiveData<String> get() = _errorText

    fun authenticate(context: Context, navController: NavController) {
        _errorText.value = ""
        val sharedPreferencesManager = SharedPreferencesManager(context = context)
        val storedPin = sharedPreferencesManager.getPIN()
        if(storedPin != _pin.value) {
            _errorText.value = "Invalid Passcode"
            _pin.value = ""
            return;
        }
        navController.navigate(Screen.HomeScreen.route){
            popUpTo(0)
        }
    }
}


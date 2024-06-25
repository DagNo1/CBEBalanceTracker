package tech.dagimtesfaye.cbe_balance_tracker.data.repository

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "my_prefs"
        private const val KEY_NAME = "key_name"
        private const val KEY_PIN = "key_pin"
    }

    fun saveName(name: String) {
        prefs.edit().putString(KEY_NAME, name).apply()
    }

    fun getName(): String? {
        return prefs.getString(KEY_NAME, null)
    }

    fun savePin(name: String) {
        prefs.edit().putString(KEY_PIN, name).apply()
    }

    fun getPIN(): String? {
        return prefs.getString(KEY_PIN, null)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}

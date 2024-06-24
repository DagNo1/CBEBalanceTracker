package tech.dagimtesfaye.cbe_balance_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import tech.dagimtesfaye.cbe_balance_tracker.navigation.NavGraph
import tech.dagimtesfaye.cbe_balance_tracker.ui.theme.CBEBalanceTrackerTheme
import tech.dagimtesfaye.cbe_balance_tracker.view.home.HomeScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.home.HomeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CBEBalanceTrackerTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                NavGraph(navController = navController)
            }
        }
    }
}

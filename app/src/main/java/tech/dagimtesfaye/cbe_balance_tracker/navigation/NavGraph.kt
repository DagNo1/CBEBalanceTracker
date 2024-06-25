package tech.dagimtesfaye.cbe_balance_tracker.navigation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import tech.dagimtesfaye.cbe_balance_tracker.data.repository.SharedPreferencesManager
import tech.dagimtesfaye.cbe_balance_tracker.view.home.HomeScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.login.LoginScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.onboarding.OnboardingScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.profileSetup.ProfilePinSetupScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.profileSetup.ProfileSetupScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.termsAndAgreements.TermsAndAgreementsScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    val readSmsPermission = rememberPermissionState(
        Manifest.permission.READ_SMS
    )
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context = context)
    val isFirstInstance = sharedPreferencesManager.isFirstInstance()


    // Set the startDestination in the NavHost based on conditions
    // If the user is opening the app for the first time = redirect them to onboarding
    // If the user has used the app before but if the sms read permission is not accepted = redirect them to TermsAndConditions
    //  If the user has used the app before and noting is wrong = redirect them to login
    NavHost(
        navController = navController,
        startDestination = when {
            isFirstInstance -> Screen.OnboardingScreen.route
            !readSmsPermission.status.isGranted -> Screen.TermsAndConditions.route
            else -> Screen.Login.route
        }
    ) {
        composable(route = Screen.TermsAndConditions.route) {
            TermsAndAgreementsScreen(onPermissionGranted = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            })
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(navController)
        }
        composable(route = Screen.ProfileSetupScreen.route) {
            ProfileSetupScreen(navController)
        }
        composable(route = Screen.ProfilePinSetupScreen.route) {
            ProfilePinSetupScreen(navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
    }
}
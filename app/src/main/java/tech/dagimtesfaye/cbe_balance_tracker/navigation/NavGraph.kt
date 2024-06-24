package tech.dagimtesfaye.cbe_balance_tracker.navigation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import tech.dagimtesfaye.cbe_balance_tracker.view.home.HomeScreen
import tech.dagimtesfaye.cbe_balance_tracker.view.termsAndAgreements.TermsAndAgreementsScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    val readSmsPermission = rememberPermissionState(
        Manifest.permission.READ_SMS
    )
    NavHost(
        navController = navController,
        startDestination = if (readSmsPermission.status.isGranted) Screen.TermsAndConditions.route else Screen.TermsAndConditions.route
    ) {
        composable(route = Screen.TermsAndConditions.route) {
            TermsAndAgreementsScreen(onPermissionGranted = {
                navController.popBackStack()
                navController.navigate(Screen.HomeScreen.route)
            })
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}
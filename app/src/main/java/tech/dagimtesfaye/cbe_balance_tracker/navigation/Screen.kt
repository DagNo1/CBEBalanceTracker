package tech.dagimtesfaye.cbe_balance_tracker.navigation

sealed class Screen(val route: String) {
    object TermsAndConditions : Screen(route = "terms_and_conditions_screen")
    object HomeScreen : Screen(route = "home_screen")
    object OnboardingScreen : Screen(route = "onboarding_screen")
    object ProfileSetupScreen : Screen(route = "profile_setup_screen")
    object ProfilePinSetupScreen : Screen(route = "profile_pin_setup_screen")
    object Login : Screen(route = "login_screen")
}
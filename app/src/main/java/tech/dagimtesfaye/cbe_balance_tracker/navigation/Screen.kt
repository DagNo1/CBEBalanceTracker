package tech.dagimtesfaye.cbe_balance_tracker.navigation

sealed class Screen(val route: String) {
    object TermsAndConditions : Screen(route = "terms_and_conditions_screen")
    object HomeScreen : Screen(route = "home_screen")
    object OnboardingScreen : Screen(route = "onboarding_screen")
}
package com.dayyan.quitvape.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object OnboardingWelcome : Screen("onboarding_welcome")
    data object UsageSetup : Screen("usage_setup")
    data object Home : Screen("home")
    data object Craving : Screen("craving")
    data object DailyCheckIn : Screen("daily_check_in")
    data object Progress : Screen("progress")
    data object Profile : Screen("profile")
    data object Settings : Screen("settings")

    data object Emergency : Screen("emergency")
    data object Radar : Screen("radar")
    data object FutureSelf : Screen("future_self")
}
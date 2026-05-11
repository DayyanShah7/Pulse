package com.dayyan.quitvape.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dayyan.quitvape.ui.screens.checkin.DailyCheckInScreen
import com.dayyan.quitvape.ui.screens.craving.CravingScreen
import com.dayyan.quitvape.ui.screens.emergency.EmergencyModeScreen
import com.dayyan.quitvape.ui.screens.future.FutureSelfScreen
import com.dayyan.quitvape.ui.screens.home.HomeScreen
import com.dayyan.quitvape.ui.screens.onboarding.OnboardingWelcomeScreen
import com.dayyan.quitvape.ui.screens.profile.ProfileScreen
import com.dayyan.quitvape.ui.screens.progress.ProgressScreen
import com.dayyan.quitvape.ui.screens.radar.CravingRadarScreen
import com.dayyan.quitvape.ui.screens.settings.SettingsScreen
import com.dayyan.quitvape.viewmodel.UserProgressViewModel

@Composable
fun AppNavigation(
    progressViewModel: UserProgressViewModel = viewModel()
) {
    val navController = rememberNavController()
    val progress by progressViewModel.progress.collectAsState()

    val startScreen = if (progress.isOnboardingCompleted) {
        Screen.Home.route
    } else {
        Screen.OnboardingWelcome.route
    }

    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        composable(Screen.OnboardingWelcome.route) {
            OnboardingWelcomeScreen { userName, dailySpending, puffsPerDay, quitReason ->
                progressViewModel.completeOnboarding(
                    userName = userName,
                    dailySpending = dailySpending,
                    puffsPerDay = puffsPerDay,
                    quitReason = quitReason
                )

                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.OnboardingWelcome.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(Screen.Home.route) {
            HomeScreen(
                puffsAvoided = progress.puffsAvoided,
                moneySaved = progress.moneySaved,
                xp = progress.xp,
                streak = progress.streak,
                costPerPuff = progress.costPerPuff,
                onResistUrge = {
                    progressViewModel.resistUrge(progress.costPerPuff)
                },
                onEmergencyClick = {
                    navController.navigate(Screen.Emergency.route)
                },
                onRadarClick = {
                    navController.navigate(Screen.Radar.route)
                },
                onFutureSelfClick = {
                    navController.navigate(Screen.FutureSelf.route)
                },
                onProgressClick = {
                    navController.navigate(Screen.Progress.route)
                },
                onDailyCheckInClick = {
                    navController.navigate(Screen.DailyCheckIn.route)
                },
                onCravingClick = {
                    navController.navigate(Screen.Craving.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(Screen.Emergency.route) {
            EmergencyModeScreen(
                moneySaved = progress.moneySaved,
                puffsAvoided = progress.puffsAvoided,
                streak = progress.streak,
                onSuccess = {
                    progressViewModel.emergencyWin(progress.costPerPuff)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Radar.route) {
            CravingRadarScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.FutureSelf.route) {
            FutureSelfScreen(
                puffsAvoided = progress.puffsAvoided,
                moneySaved = progress.moneySaved,
                xp = progress.xp,
                streak = progress.streak,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Progress.route) {
            ProgressScreen(
                puffsAvoided = progress.puffsAvoided,
                moneySaved = progress.moneySaved,
                xp = progress.xp,
                streak = progress.streak,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.DailyCheckIn.route) {
            DailyCheckInScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Craving.route) {
            CravingScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                userName = progress.userName,
                onBack = {
                    navController.popBackStack()
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                dailySpending = progress.dailySpending,
                puffsPerDay = progress.puffsPerDay,
                quitReason = progress.quitReason,
                onResetProgress = {
                    progressViewModel.resetProgress()
                    navController.navigate(Screen.OnboardingWelcome.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
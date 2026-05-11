package com.dayyan.quitvape.data.model

data class UserProgress(
    val puffsAvoided: Int = 0,
    val moneySaved: Double = 0.0,
    val xp: Int = 0,
    val streak: Int = 1,

    val isOnboardingCompleted: Boolean = false,
    val dailySpending: Double = 0.0,
    val puffsPerDay: Int = 1,
    val quitReason: String = "",

    val userName: String = ""
) {
    val costPerPuff: Double
        get() = if (puffsPerDay > 0) dailySpending / puffsPerDay else 0.0
}
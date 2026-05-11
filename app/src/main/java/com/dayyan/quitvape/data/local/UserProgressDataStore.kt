package com.dayyan.quitvape.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dayyan.quitvape.data.model.UserProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_progress")

class UserProgressDataStore(
    private val context: Context
) {
    private object Keys {
        val PUFFS_AVOIDED = intPreferencesKey("puffs_avoided")
        val MONEY_SAVED = doublePreferencesKey("money_saved")
        val XP = intPreferencesKey("xp")
        val STREAK = intPreferencesKey("streak")

        val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
        val DAILY_SPENDING = doublePreferencesKey("daily_spending")
        val PUFFS_PER_DAY = intPreferencesKey("puffs_per_day")
        val QUIT_REASON = stringPreferencesKey("quit_reason")

        val USER_NAME = stringPreferencesKey("user_name")
    }

    val progressFlow: Flow<UserProgress> = context.dataStore.data.map { prefs ->
        UserProgress(
            puffsAvoided = prefs[Keys.PUFFS_AVOIDED] ?: 0,
            moneySaved = prefs[Keys.MONEY_SAVED] ?: 0.0,
            xp = prefs[Keys.XP] ?: 0,
            streak = prefs[Keys.STREAK] ?: 1,
            isOnboardingCompleted = prefs[Keys.ONBOARDING_DONE] ?: false,
            dailySpending = prefs[Keys.DAILY_SPENDING] ?: 0.0,
            puffsPerDay = prefs[Keys.PUFFS_PER_DAY] ?: 1,
            quitReason = prefs[Keys.QUIT_REASON] ?: "",

            userName = prefs[Keys.USER_NAME] ?: ""
        )
    }

    suspend fun completeOnboarding(
        userName: String,
        dailySpending: Double,
        puffsPerDay: Int,
        quitReason: String
    ) {
        context.dataStore.edit { prefs ->
            prefs[Keys.ONBOARDING_DONE] = true
            prefs[Keys.DAILY_SPENDING] = dailySpending
            prefs[Keys.PUFFS_PER_DAY] = puffsPerDay
            prefs[Keys.QUIT_REASON] = quitReason

            prefs[Keys.USER_NAME] = userName
        }
    }

    suspend fun addResistedUrge(costPerPuff: Double) {
        context.dataStore.edit { prefs ->
            prefs[Keys.PUFFS_AVOIDED] = (prefs[Keys.PUFFS_AVOIDED] ?: 0) + 1
            prefs[Keys.MONEY_SAVED] = (prefs[Keys.MONEY_SAVED] ?: 0.0) + costPerPuff
            prefs[Keys.XP] = (prefs[Keys.XP] ?: 0) + 10
            prefs[Keys.STREAK] = prefs[Keys.STREAK] ?: 1
        }
    }

    suspend fun addEmergencyWin(costPerPuff: Double) {
        context.dataStore.edit { prefs ->
            prefs[Keys.PUFFS_AVOIDED] = (prefs[Keys.PUFFS_AVOIDED] ?: 0) + 1
            prefs[Keys.MONEY_SAVED] = (prefs[Keys.MONEY_SAVED] ?: 0.0) + costPerPuff
            prefs[Keys.XP] = (prefs[Keys.XP] ?: 0) + 25
            prefs[Keys.STREAK] = prefs[Keys.STREAK] ?: 1
        }
    }

    suspend fun resetProgress() {
        context.dataStore.edit { it.clear() }
    }
}
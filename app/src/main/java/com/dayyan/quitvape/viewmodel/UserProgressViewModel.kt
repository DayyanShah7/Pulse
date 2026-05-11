package com.dayyan.quitvape.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dayyan.quitvape.data.local.UserProgressDataStore
import com.dayyan.quitvape.data.model.UserProgress
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserProgressViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataStore = UserProgressDataStore(application)

    val progress = dataStore.progressFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserProgress()
    )

    fun resistUrge(costPerPuff: Double) {
        viewModelScope.launch {
            dataStore.addResistedUrge(costPerPuff)
        }
    }

    fun emergencyWin(costPerPuff: Double) {
        viewModelScope.launch {
            dataStore.addEmergencyWin(costPerPuff)
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            dataStore.resetProgress()
        }
    }
    fun completeOnboarding(
        dailySpending: Double,
        puffsPerDay: Int,
        quitReason: String,

        userName: String,
    ) {
        viewModelScope.launch {
            dataStore.completeOnboarding(
                dailySpending = dailySpending,
                puffsPerDay = puffsPerDay,
                quitReason = quitReason,

                userName = userName,
            )
        }
    }
}
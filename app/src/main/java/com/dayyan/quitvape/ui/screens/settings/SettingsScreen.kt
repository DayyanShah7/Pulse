package com.dayyan.quitvape.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun SettingsScreen(
    dailySpending: Double,
    puffsPerDay: Int,
    quitReason: String,
    onResetProgress: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 22.dp, vertical = 42.dp)
    ) {
        Text(
            text = "Settings",
            color = AppColors.WhiteText,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Daily spending: RM %.2f".format(dailySpending),
            color = AppColors.WhiteText,
            fontSize = 16.sp
        )

        Text(
            text = "Estimated puffs/day: $puffsPerDay",
            color = AppColors.WhiteText,
            fontSize = 16.sp
        )

        Text(
            text = "Reason: $quitReason",
            color = AppColors.MutedText,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Reset All Progress",
            isDanger = true,
            onClick = onResetProgress
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )
    }
}
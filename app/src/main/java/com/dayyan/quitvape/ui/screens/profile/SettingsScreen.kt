package com.dayyan.quitvape.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dayyan.quitvape.ui.components.PremiumCard
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    var remindersEnabled by remember { mutableStateOf(true) }
    var cravingAlertsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        Text(
            text = "Settings",
            color = AppColors.WhiteText,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(28.dp))

        PremiumCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                SettingRow(
                    title = "Daily reminders",
                    subtitle = "Get reminded to check in",
                    checked = remindersEnabled,
                    onCheckedChange = { remindersEnabled = it }
                )

                SettingRow(
                    title = "Craving alerts",
                    subtitle = "Support during risky times",
                    checked = cravingAlertsEnabled,
                    onCheckedChange = { cravingAlertsEnabled = it }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )
    }
}

@Composable
private fun SettingRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = AppColors.WhiteText,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                color = AppColors.MutedText
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
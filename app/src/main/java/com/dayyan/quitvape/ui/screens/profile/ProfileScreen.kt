package com.dayyan.quitvape.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dayyan.quitvape.ui.components.PremiumCard
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun ProfileScreen(

    userName: String,
    onBack: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        Box(
            modifier = Modifier
                .size(96.dp)
                .background(AppColors.PrimaryGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "P",
                color = AppColors.Background,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = userName.ifBlank { "Puffless User" },
            color = AppColors.WhiteText,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Building control daily",
            color = AppColors.MutedText
        )

        Spacer(modifier = Modifier.height(32.dp))

        PremiumCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProfileRow("Current streak", "3 days")
                ProfileRow("Quit mode", "Gradual reduction")
                ProfileRow("Daily baseline", "200 puffs")
                ProfileRow("Average savings", "RM 6.50/day")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Settings",
            onClick = onSettingsClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )
    }
}

@Composable
private fun ProfileRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = AppColors.MutedText)
        Text(value, color = AppColors.WhiteText, fontWeight = FontWeight.SemiBold)
    }
}
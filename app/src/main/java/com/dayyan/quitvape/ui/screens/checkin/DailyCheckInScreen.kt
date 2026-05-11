package com.dayyan.quitvape.ui.screens.checkin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.components.PremiumCard
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun DailyCheckInScreen(
    onBack: () -> Unit,
    onSaved: () -> Unit
) {
    var puffsToday by remember { mutableStateOf("") }
    var cravingsToday by remember { mutableStateOf("") }

    val isValid = puffsToday.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(52.dp))

            Text(
                text = "Daily Check-In",
                color = AppColors.WhiteText,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "No judgement. Just honest tracking.",
                color = AppColors.MutedText,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(28.dp))

            PremiumCard {
                Column {
                    CheckInTextField(
                        value = puffsToday,
                        onValueChange = { puffsToday = it },
                        label = "How many puffs today?"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CheckInTextField(
                        value = cravingsToday,
                        onValueChange = { cravingsToday = it },
                        label = "How many cravings?"
                    )
                }
            }
        }

        Column {
            PrimaryButton(
                text = "Save check-in",
                enabled = isValid,
                onClick = onSaved
            )

            Spacer(modifier = Modifier.height(12.dp))

            PrimaryButton(
                text = "Back",
                onClick = onBack
            )
        }
    }
}

@Composable
private fun CheckInTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = AppColors.WhiteText,
            unfocusedTextColor = AppColors.WhiteText,
            focusedBorderColor = AppColors.PrimaryGreen,
            unfocusedBorderColor = AppColors.Border,
            focusedLabelColor = AppColors.PrimaryGreen,
            unfocusedLabelColor = AppColors.MutedText,
            cursorColor = AppColors.PrimaryGreen
        )
    )
}
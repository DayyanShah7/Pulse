package com.dayyan.quitvape.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun OnboardingWelcomeScreen(
    onFinish: (
        userName: String,
        dailySpending: Double,
        puffsPerDay: Int,
        quitReason: String
    ) -> Unit
) {
    var userName by remember { mutableStateOf("") }
    var dailySpending by remember { mutableStateOf("") }
    var puffsPerDay by remember { mutableStateOf("") }
    var quitReason by remember { mutableStateOf("") }

    val spendingValue = dailySpending.toDoubleOrNull() ?: 0.0
    val puffsValue = puffsPerDay.toIntOrNull() ?: 0

    val canContinue =
        userName.isNotBlank() &&
                spendingValue > 0 &&
                puffsValue > 0 &&
                quitReason.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 22.dp, vertical = 42.dp)
    ) {
        Text(
            text = "Set Up Your Quit Plan",
            color = AppColors.WhiteText,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We’ll calculate how much money you save every time you resist a puff.",
            color = AppColors.MutedText,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Your name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = onboardingTextFieldColors()
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = dailySpending,
            onValueChange = { dailySpending = it },
            label = { Text("Daily vape spending e.g. 20") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = onboardingTextFieldColors()
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = puffsPerDay,
            onValueChange = { puffsPerDay = it },
            label = { Text("Estimated puffs per day e.g. 200") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = onboardingTextFieldColors()
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = quitReason,
            onValueChange = { quitReason = it },
            label = { Text("Why do you want to quit?") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            colors = onboardingTextFieldColors()
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Start My Journey",
            enabled = canContinue
        ) {
            onFinish(
                userName.trim(),
                spendingValue,
                puffsValue,
                quitReason.trim()
            )
        }
    }
}

@Composable
private fun onboardingTextFieldColors() =
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = AppColors.WhiteText,
        unfocusedTextColor = AppColors.WhiteText,
        focusedBorderColor = AppColors.PrimaryGreen,
        unfocusedBorderColor = AppColors.Border,
        focusedLabelColor = AppColors.PrimaryGreen,
        unfocusedLabelColor = AppColors.MutedText,
        cursorColor = AppColors.PrimaryGreen
    )
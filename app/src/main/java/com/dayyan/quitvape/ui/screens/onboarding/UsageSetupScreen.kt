package com.dayyan.quitvape.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun UsageSetupScreen(
    onSetupComplete: () -> Unit
) {
    var puffsPerDay by remember { mutableStateOf("") }
    var vapePrice by remember { mutableStateOf("") }
    var lastsDays by remember { mutableStateOf("") }

    val isValid = puffsPerDay.isNotBlank() &&
            vapePrice.isNotBlank() &&
            lastsDays.isNotBlank()

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
                text = "Let’s understand\nyour current usage.",
                color = AppColors.WhiteText,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "This helps Puffless calculate puffs avoided and money saved.",
                color = AppColors.MutedText,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            SetupTextField(
                value = puffsPerDay,
                onValueChange = { puffsPerDay = it },
                label = "Average puffs per day"
            )

            Spacer(modifier = Modifier.height(16.dp))

            SetupTextField(
                value = vapePrice,
                onValueChange = { vapePrice = it },
                label = "Vape price"
            )

            Spacer(modifier = Modifier.height(16.dp))

            SetupTextField(
                value = lastsDays,
                onValueChange = { lastsDays = it },
                label = "One vape lasts how many days?"
            )
        }

        PrimaryButton(
            text = "Create my plan",
            enabled = isValid,
            onClick = onSetupComplete
        )
    }
}

@Composable
private fun SetupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
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
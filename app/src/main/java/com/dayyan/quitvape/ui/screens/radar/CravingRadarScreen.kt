package com.dayyan.quitvape.ui.screens.radar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun CravingRadarScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 20.dp, vertical = 38.dp)
    ) {
        Text(
            text = "Craving Radar",
            color = AppColors.WhiteText,
            fontSize = 32.sp
        )

        Text(
            text = "Your app will learn high-risk times based on your cravings.",
            color = AppColors.MutedText,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        RadarCard(
            title = "Morning Risk",
            level = 0.35f,
            label = "Low"
        )

        RadarCard(
            title = "After Meal Risk",
            level = 0.72f,
            label = "High"
        )

        RadarCard(
            title = "Late Night Risk",
            level = 0.58f,
            label = "Medium"
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "Later, this will use your real craving logs to predict when you are most likely to vape.",
            color = AppColors.MutedText,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )
    }
}

@Composable
private fun RadarCard(
    title: String,
    level: Float,
    label: String
) {
    Column(
        modifier = Modifier
            .padding(bottom = 14.dp)
            .fillMaxWidth()
            .background(AppColors.CardBackground, RoundedCornerShape(22.dp))
            .border(1.dp, AppColors.Border, RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Text(
            text = title,
            color = AppColors.WhiteText,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        LinearProgressIndicator(
            progress = { level },
            modifier = Modifier.fillMaxWidth(),
            color = AppColors.PrimaryGreen,
            trackColor = AppColors.DarkBorder
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Risk level: $label",
            color = AppColors.MutedText,
            fontSize = 14.sp
        )
    }
}
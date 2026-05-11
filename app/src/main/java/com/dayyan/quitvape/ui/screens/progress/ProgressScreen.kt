package com.dayyan.quitvape.ui.screens.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.components.StatCard
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun ProgressScreen(
    puffsAvoided: Int,
    moneySaved: Double,
    xp: Int,
    streak: Int,
    onBack: () -> Unit
) {
    val level = (xp / 100) + 1
    val levelProgress = (xp % 100) / 100f
    val recoveryProgress = (puffsAvoided / 100f).coerceAtMost(1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp, vertical = 42.dp)
    ) {
        Text(
            text = "Progress",
            color = AppColors.WhiteText,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Small wins become a new identity.",
            color = AppColors.MutedText,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(
                title = "Money Saved",
                value = "RM %.2f".format(moneySaved),
                subtitle = "Since you started",
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Puffs Avoided",
                value = "$puffsAvoided",
                subtitle = "Every puff counts",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(
                title = "Current Streak",
                value = "$streak day",
                subtitle = "Protect the chain",
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Level",
                value = "$level",
                subtitle = "$xp XP total",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        ProgressCard(
            title = "Level Progress",
            subtitle = "Reach 100 XP to level up.",
            progress = levelProgress,
            valueText = "${(levelProgress * 100).toInt()}%"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProgressCard(
            title = "Recovery Progress",
            subtitle = "Based on puffs avoided. This is a motivational estimate.",
            progress = recoveryProgress,
            valueText = "${(recoveryProgress * 100).toInt()}%"
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoCard(
            title = "Health Milestone",
            description = if (puffsAvoided == 0) {
                "Start by resisting your first urge. Your progress will appear here."
            } else {
                "You have already avoided $puffsAvoided puffs. That is real self-control."
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun ProgressCard(
    title: String,
    subtitle: String,
    progress: Float,
    valueText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.CardBackground, RoundedCornerShape(22.dp))
            .border(1.dp, AppColors.Border, RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = AppColors.WhiteText,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = valueText,
                color = AppColors.PrimaryGreen,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            color = AppColors.MutedText
        )

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = AppColors.PrimaryGreen,
            trackColor = AppColors.SelectedCardBackground
        )
    }
}

@Composable
private fun InfoCard(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.CardBackground, RoundedCornerShape(22.dp))
            .border(1.dp, AppColors.Border, RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Text(
            text = title,
            color = AppColors.WhiteText,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = description,
            color = AppColors.MutedText
        )
    }
}
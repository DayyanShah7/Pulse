package com.dayyan.quitvape.ui.screens.future

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun FutureSelfScreen(
    puffsAvoided: Int,
    moneySaved: Double,
    xp: Int,
    streak: Int,
    onBack: () -> Unit
) {
    val level = (xp / 100) + 1
    val recoveryProgress = (puffsAvoided / 100f).coerceAtMost(1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 20.dp, vertical = 38.dp)
    ) {
        Text(
            text = "Future Self",
            color = AppColors.WhiteText,
            fontSize = 32.sp
        )

        Text(
            text = "Every urge resisted builds the next version of you.",
            color = AppColors.MutedText,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(AppColors.SelectedCardBackground, CircleShape)
                    .border(2.dp, AppColors.PrimaryGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "LVL $level",
                        color = AppColors.PrimaryGreen,
                        fontSize = 30.sp
                    )

                    Text(
                        text = "Cleaner Self",
                        color = AppColors.WhiteText,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(34.dp))

        ProgressBlock(
            title = "Lung Recovery",
            progress = recoveryProgress,
            value = "${(recoveryProgress * 100).toInt()}%"
        )

        ProgressBlock(
            title = "Self-Control Growth",
            progress = (streak / 30f).coerceAtMost(1f),
            value = "$streak / 30 days"
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.CardBackground, RoundedCornerShape(22.dp))
                .border(1.dp, AppColors.Border, RoundedCornerShape(22.dp))
                .padding(18.dp)
        ) {
            Text(
                text = "Your Wins",
                color = AppColors.WhiteText,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "💨 $puffsAvoided puffs avoided",
                color = AppColors.MutedText,
                fontSize = 15.sp
            )

            Text(
                text = "💰 RM %.2f saved".format(moneySaved),
                color = AppColors.MutedText,
                fontSize = 15.sp
            )

            Text(
                text = "🔥 $streak day streak",
                color = AppColors.MutedText,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )
    }
}

@Composable
private fun ProgressBlock(
    title: String,
    progress: Float,
    value: String
) {
    Column(
        modifier = Modifier
            .padding(bottom = 18.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = AppColors.WhiteText,
                fontSize = 16.sp
            )

            Text(
                text = value,
                color = AppColors.PrimaryGreen,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = AppColors.PrimaryGreen,
            trackColor = AppColors.DarkBorder
        )
    }
}
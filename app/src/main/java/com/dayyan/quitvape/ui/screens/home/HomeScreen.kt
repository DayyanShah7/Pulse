package com.dayyan.quitvape.ui.screens.home

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.components.StatCard
import com.dayyan.quitvape.ui.theme.AppColors
import kotlinx.coroutines.delay

import androidx.compose.foundation.clickable

@Composable
fun HomeScreen(
    puffsAvoided: Int,
    moneySaved: Double,
    xp: Int,
    streak: Int,
    costPerPuff: Double,
    onResistUrge: () -> Unit,
    onEmergencyClick: () -> Unit,
    onRadarClick: () -> Unit,
    onFutureSelfClick: () -> Unit,
    onProgressClick: () -> Unit,
    onDailyCheckInClick: () -> Unit,
    onCravingClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    var showSavedAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(showSavedAnimation) {
        if (showSavedAnimation) {
            delay(900)
            showSavedAnimation = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 38.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "PuffLess",
                        color = AppColors.WhiteText,
                        fontSize = 34.sp
                    )

                    Text(
                        text = "Turn every resisted urge into progress.",
                        color = AppColors.MutedText,
                        fontSize = 15.sp
                    )
                }

                Text(
                    text = "⚙️",
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(AppColors.CardBackground, CircleShape)
                        .padding(10.dp)
                        .noRippleClickable { onSettingsClick() }
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard("Money Saved", "RM %.2f".format(moneySaved), Modifier.weight(1f))
                StatCard("Puffs Avoided", "$puffsAvoided", Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard("Streak", "$streak day", Modifier.weight(1f))
                StatCard("XP", "$xp", Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(34.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(175.dp)
                        .scale(if (showSavedAnimation) 1.08f else 1f)
                        .background(AppColors.SelectedCardBackground, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("I resisted", color = AppColors.WhiteText, fontSize = 26.sp)

                        Text(
                            "Tap after beating an urge",
                            color = AppColors.MutedText,
                            fontSize = 13.sp
                        )
                    }
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = showSavedAnimation,
                    modifier = Modifier.align(Alignment.TopCenter),
                    enter = fadeIn() + slideInVertically(initialOffsetY = { 50 }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { -80 })
                ) {
                    Text(
                        text = "+ RM %.2f saved".format(costPerPuff),
                        color = AppColors.PrimaryGreen,
                        fontSize = 26.sp
                    )
                }
            }

            PrimaryButton("I resisted one urge") {
                onResistUrge()
                showSavedAnimation = true
            }

            Spacer(modifier = Modifier.height(14.dp))

            PrimaryButton(
                text = "Emergency Mode",
                isDanger = true,
                onClick = onEmergencyClick
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Your Tools",
                color = AppColors.WhiteText,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeMiniButton(
                    title = "Radar",
                    emoji = "🧠",
                    modifier = Modifier.weight(1f),
                    onClick = onRadarClick
                )

                HomeMiniButton(
                    title = "Future",
                    emoji = "🌱",
                    modifier = Modifier.weight(1f),
                    onClick = onFutureSelfClick
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeMiniButton(
                    title = "Progress",
                    emoji = "📊",
                    modifier = Modifier.weight(1f),
                    onClick = onProgressClick
                )

                HomeMiniButton(
                    title = "Check-In",
                    emoji = "📝",
                    modifier = Modifier.weight(1f),
                    onClick = onDailyCheckInClick
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeMiniButton(
                    title = "Craving",
                    emoji = "💨",
                    modifier = Modifier.weight(1f),
                    onClick = onCravingClick
                )

                HomeMiniButton(
                    title = "Profile",
                    emoji = "👤",
                    modifier = Modifier.weight(1f),
                    onClick = onProfileClick
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun HomeMiniButton(
    title: String,
    emoji: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .height(92.dp)
            .background(AppColors.CardBackground, androidx.compose.foundation.shape.RoundedCornerShape(22.dp))
            .noRippleClickable { onClick() }
            .padding(14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = emoji, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, color = AppColors.WhiteText, fontSize = 15.sp)
    }
}

private fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    return this.then(
        androidx.compose.ui.Modifier.clickable(
            indication = null,
            interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource()
        ) {
            onClick()
        }
    )
}
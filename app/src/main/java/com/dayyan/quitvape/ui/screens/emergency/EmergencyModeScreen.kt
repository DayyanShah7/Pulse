package com.dayyan.quitvape.ui.screens.emergency

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.theme.AppColors
import kotlinx.coroutines.delay

@Composable
fun EmergencyModeScreen(
    moneySaved: Double,
    puffsAvoided: Int,
    streak: Int,
    onSuccess: () -> Unit,
    onBack: () -> Unit
) {
    var selectedDefense by remember { mutableStateOf<String?>(null) }
    var secondsLeft by remember { mutableIntStateOf(120) }
    var running by remember { mutableStateOf(false) }

    LaunchedEffect(running, selectedDefense) {
        if (running) {
            while (secondsLeft > 0) {
                delay(1000)
                secondsLeft--
            }
            running = false
        }
    }

    if (selectedDefense == null) {
        DefenseSelectionScreen(
            moneySaved = moneySaved,
            puffsAvoided = puffsAvoided,
            streak = streak,
            onDefenseSelected = { defense ->
                selectedDefense = defense
                secondsLeft = 120
                running = true
            },
            onBack = onBack
        )
    } else {
        ActiveDefenseScreen(
            selectedDefense = selectedDefense!!,
            secondsLeft = secondsLeft,
            running = running,
            onSuccess = onSuccess,
            onChooseAnother = {
                selectedDefense = null
                running = false
                secondsLeft = 120
            },
            onBack = onBack
        )
    }
}

@Composable
private fun DefenseSelectionScreen(
    moneySaved: Double,
    puffsAvoided: Int,
    streak: Int,
    onDefenseSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 38.dp)
    ) {
        Text("Emergency Mode", color = AppColors.CravingOrange, fontSize = 32.sp)

        Text(
            text = "Choose a psychology-backed defense.",
            color = AppColors.MutedText,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        EmergencyStats(moneySaved, puffsAvoided, streak)

        Spacer(modifier = Modifier.height(28.dp))

        DefenseCard(
            title = "🌊 Urge Surfing",
            description = "Ride the craving like a wave until it fades."
        ) {
            onDefenseSelected("surf")
        }

        DefenseCard(
            title = "⏳ Delay Timer",
            description = "Delay action for 2 minutes and break the habit loop."
        ) {
            onDefenseSelected("delay")
        }

        DefenseCard(
            title = "✊ Competing Response",
            description = "Replace the urge with a physical action."
        ) {
            onDefenseSelected("replace")
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Back",
            isDanger = true,
            onClick = onBack
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun ActiveDefenseScreen(
    selectedDefense: String,
    secondsLeft: Int,
    running: Boolean,
    onSuccess: () -> Unit,
    onChooseAnother: () -> Unit,
    onBack: () -> Unit
) {
    val progress = ((120 - secondsLeft) / 120f).coerceIn(0f, 1f)

    val infiniteTransition = rememberInfiniteTransition(label = "smoothPulse")
    val smoothScale by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "circlePulse"
    )

    val toneGenerator = remember {
        ToneGenerator(AudioManager.STREAM_MUSIC, 25)
    }

    DisposableEffect(Unit) {
        onDispose {
            toneGenerator.release()
        }
    }

    LaunchedEffect(running, secondsLeft) {
        if (running && secondsLeft > 0 && secondsLeft % 2 == 0) {
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 55)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 38.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = defenseTitle(selectedDefense),
            color = AppColors.WhiteText,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = defenseSubtitle(selectedDefense),
            color = AppColors.MutedText,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(34.dp))

        Box(
            modifier = Modifier
                .size(185.dp)
                .scale(smoothScale)
                .background(AppColors.SelectedCardBackground, CircleShape)
                .border(2.dp, AppColors.PrimaryGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${secondsLeft}s",
                    color = AppColors.PrimaryGreen,
                    fontSize = 38.sp
                )

                Text(
                    text = "stay with it",
                    color = AppColors.MutedText,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = AppColors.PrimaryGreen,
            trackColor = AppColors.DarkBorder
        )

        Spacer(modifier = Modifier.height(24.dp))

        DefenseInstructionCard(selectedDefense)

        Spacer(modifier = Modifier.height(28.dp))

        PrimaryButton(
            text = "I beat the urge",
            onClick = onSuccess
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "Choose Another Defense",
            onClick = onChooseAnother
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "Back",
            isDanger = true,
            onClick = onBack
        )

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun DefenseInstructionCard(type: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.CardBackground, RoundedCornerShape(22.dp))
            .border(1.dp, AppColors.Border, RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Text(
            text = "What to do now",
            color = AppColors.WhiteText,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = defenseMessage(type),
            color = AppColors.MutedText,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun EmergencyStats(
    moneySaved: Double,
    puffsAvoided: Int,
    streak: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.CardBackground, RoundedCornerShape(22.dp))
            .border(1.dp, AppColors.Border, RoundedCornerShape(22.dp))
            .padding(18.dp)
    ) {
        Text("Before you vape, remember:", color = AppColors.WhiteText, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "💰 RM %.2f saved".format(moneySaved),
            color = AppColors.PrimaryGreen,
            fontSize = 16.sp
        )

        Text(
            "💨 $puffsAvoided puffs avoided",
            color = AppColors.WhiteText,
            fontSize = 16.sp
        )

        Text(
            "🔥 $streak day streak protected",
            color = AppColors.WarningYellow,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun DefenseCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .background(AppColors.CardBackground, RoundedCornerShape(20.dp))
            .border(1.dp, AppColors.Border, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = title, color = AppColors.WhiteText, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = description, color = AppColors.MutedText, fontSize = 14.sp)
    }
}

private fun defenseTitle(type: String): String {
    return when (type) {
        "surf" -> "Urge Surfing"
        "delay" -> "Delay Timer"
        "replace" -> "Competing Response"
        else -> "Defense Mode"
    }
}

private fun defenseSubtitle(type: String): String {
    return when (type) {
        "surf" -> "Do not fight the wave. Ride it."
        "delay" -> "Delay the action until the craving weakens."
        "replace" -> "Replace the vape ritual with a safer action."
        else -> ""
    }
}

private fun defenseMessage(type: String): String {
    return when (type) {
        "surf" -> "Notice where the craving is in your body. Watch it rise, peak, and fade. You do not need to obey it."
        "delay" -> "Your only mission is to wait. Do not decide anything yet. Let the next 2 minutes pass first."
        "replace" -> "Drink water, walk 20 steps, squeeze your fist, or hold something cold. Replace the hand-to-mouth loop."
        else -> ""
    }
}
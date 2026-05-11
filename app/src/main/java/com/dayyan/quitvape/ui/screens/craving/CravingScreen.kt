package com.dayyan.quitvape.ui.screens.craving

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dayyan.quitvape.ui.components.PrimaryButton
import com.dayyan.quitvape.ui.components.PremiumCard
import com.dayyan.quitvape.ui.theme.AppColors
import kotlinx.coroutines.delay

@Composable
fun CravingScreen(
    onBack: () -> Unit
) {
    var secondsLeft by remember { mutableIntStateOf(180) }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.18f,
        animationSpec = infiniteRepeatable(
            animation = tween(3200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "circleScale"
    )

    val minute = secondsLeft / 60
    val second = secondsLeft % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        AppColors.Background,
                        AppColors.CardBackground
                    )
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        Text(
            text = "Craving Mode",
            color = AppColors.WhiteText,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Stay here. Let this wave pass.",
            color = AppColors.MutedText,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(54.dp))

        Box(
            modifier = Modifier
                .size(220.dp)
                .scale(scale)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            AppColors.PrimaryGreen.copy(alpha = 0.85f),
                            AppColors.PrimaryGreen.copy(alpha = 0.12f)
                        )
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Breathe",
                color = AppColors.Background,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(52.dp))

        Text(
            text = "%02d:%02d".format(minute, second),
            color = AppColors.PrimaryGreen,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        PremiumCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (secondsLeft > 0)
                    "Cravings usually peak and fade. You only need to win the next few minutes."
                else
                    "You made it through. That is real control.",
                color = AppColors.WhiteText,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "I resisted",
            onClick = onBack
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "Back",
            onClick = onBack
        )
    }
}
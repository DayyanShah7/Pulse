package com.dayyan.quitvape.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val QuitVapeColorScheme = darkColorScheme(
    background = AppColors.Background,
    surface = AppColors.CardBackground,
    primary = AppColors.PrimaryGreen,
    secondary = AppColors.CravingOrange,
    tertiary = AppColors.WarningYellow
)

@Composable
fun QuitVapeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = QuitVapeColorScheme,
        content = content
    )
}
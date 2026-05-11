package com.dayyan.quitvape.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    isDanger: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .background(
                color = if (!enabled) AppColors.DarkBorder
                else if (isDanger) AppColors.CravingOrange
                else AppColors.PrimaryGreen,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(enabled = enabled) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (!enabled)
                AppColors.MutedText
            else
                AppColors.Background,
            fontSize = 17.sp
        )
    }
}
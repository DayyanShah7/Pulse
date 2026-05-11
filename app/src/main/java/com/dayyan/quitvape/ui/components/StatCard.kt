package com.dayyan.quitvape.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayyan.quitvape.ui.theme.AppColors

@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null
) {
    Column(
        modifier = modifier
            .background(
                AppColors.CardBackground,
                RoundedCornerShape(22.dp)
            )
            .border(
                1.dp,
                AppColors.Border,
                RoundedCornerShape(22.dp)
            )
            .padding(18.dp)
    ) {

        Text(
            text = title,
            color = AppColors.MutedText,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            color = AppColors.WhiteText,
            fontSize = 24.sp
        )

        subtitle?.let {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = it,
                color = AppColors.PrimaryGreen,
                fontSize = 12.sp
            )
        }
    }
}
package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.ui.theme.IceBlue

@Composable
fun PercentageCircle(percentage: Int) {
    val size = 150.dp
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(20.dp, IceBlue, CircleShape)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$percentage%",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}
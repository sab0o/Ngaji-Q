package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun LetterBox(
    letter: String,
    state: LetterState = LetterState.Normal,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val backgroundColor = when (state) {
        LetterState.Normal -> Color.White
        LetterState.Selected -> Color(0xFF42BBE4).copy(alpha = 0.2f)  // biru muda
        LetterState.Correct -> Color(0xFF4CAF50).copy(alpha = 0.2f)   // hijau muda
        LetterState.Wrong -> Color(0xFFF44336).copy(alpha = 0.2f)     // merah muda
    }

    val borderColor = when (state) {
        LetterState.Normal -> Color(0xFF42BBE4)
        LetterState.Selected -> Color(0xFF42BBE4)
        LetterState.Correct -> Color(0xFF4CAF50)
        LetterState.Wrong -> Color(0xFFF44336)
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(60.dp)
            .border(
                BorderStroke(2.dp, borderColor),
                RoundedCornerShape(16.dp)
            )
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .let {
                if (enabled && onClick != null) it.clickable { onClick() }
                else it
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            color = Color.Black
        )
    }
}

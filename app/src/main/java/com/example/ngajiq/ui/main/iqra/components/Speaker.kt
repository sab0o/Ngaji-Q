package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ngajiq.R

@Composable
fun SpeakerButton(onSpeakerClick: () -> Unit, modifier: Modifier) {
    IconButton(
        onClick = onSpeakerClick,
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFF55D5FF), RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_speaker),
            contentDescription = "Speaker",
            modifier = Modifier.size(28.dp)
        )
    }
}

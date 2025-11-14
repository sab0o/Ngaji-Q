package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ngajiq.ui.theme.IceBlue
import com.example.ngajiq.ui.theme.PrimaryBlue
import com.example.ngajiq.R

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    onPauseClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // 🔹 Background progress (placeholder)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .padding(start = 28.dp, end = 54.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(IceBlue) // ⬅ Pakai theme
        )

        // 🔹 Actual progress bar
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(32.dp)
                .padding(start = 28.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(PrimaryBlue) // ⬅ Pakai theme
        )

        // 🔹 Profile image
        Box(
            modifier = Modifier
                .size(80.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = CircleShape,
                    ambientColor = PrimaryBlue, // ⬅ Shadow warna biru theme
                    spotColor = PrimaryBlue
                )
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color.White, shape = CircleShape)
                .border(4.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_placeholder),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
        }

        // 🔹 Pause button
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(PrimaryBlue) // ⬅ Pakai theme
                .clickable { onPauseClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "Pause Button",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

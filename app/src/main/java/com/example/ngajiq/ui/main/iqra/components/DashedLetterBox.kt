package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ngajiq.ui.theme.AquaBlue

@Composable
fun DashedLetterBox(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 15f), 0f)
    )

    Box(
        modifier = modifier
            .height(250.dp)
            .drawBehind {
                drawRoundRect(
                    color = AquaBlue,              // ⬅ warna garis pakai warna theme
                    style = stroke,
                    cornerRadius = CornerRadius(16.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Huruf",
            modifier = Modifier.size(130.dp)
        )
    }
}

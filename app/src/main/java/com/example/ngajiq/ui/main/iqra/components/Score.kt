package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ngajiq.ui.theme.IceBlue

@Composable
fun ScoreBox(correct: Int, incorrect: Int) {

    val dashEffect = PathEffect.dashPathEffect(
        floatArrayOf(20f, 20f), // panjang garis, panjang spasi
        0f
    )

    Box(
        modifier = Modifier
            .size(width = 288.dp, height = 104.dp)
            .drawBehind {
                drawRoundRect(
                    color = IceBlue,
                    style = Stroke(
                        width = 6f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(20f, 20f),
                            0f
                        )
                    ),
                    cornerRadius = CornerRadius(32f, 32f)
                )
            }
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScoreItem(value = correct, label = "Benar", modifier = Modifier.weight(1f))
            ScoreItem(value = incorrect, label = "Salah", modifier = Modifier.weight(1f))
        }
    }

}

@Composable
fun RowScope.ScoreItem(value: Int, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = value.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

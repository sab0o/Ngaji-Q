package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R


@Composable
fun MateriCard(
    title: String,
    desc: String,
    progress: Int,
    isActive: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val cardColor = if (isActive)
        Brush.horizontalGradient(listOf(Color(0xFF42A5F5), Color(0xFF1976D2)))
    else
        Brush.verticalGradient(listOf(Color.White, Color.White))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick?.invoke() }, // agar bisa diklik untuk navigasi
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(cardColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isActive) Color.White else Color.Gray
                )
                Text(
                    text = desc,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isActive) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress / 30f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(50)),
                    color = if (isActive) Color.White else Color(0xFF42A5F5),
                    trackColor = Color.LightGray,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
                )
                Text(
                    text = "$progress/30 level ${progress * 100 / 30}%",
                    fontSize = 10.sp,
                    color = if (isActive) Color.White else Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = R.drawable.iqra),
                contentDescription = "Quran",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}
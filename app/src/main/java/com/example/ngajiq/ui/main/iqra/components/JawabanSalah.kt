package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R

@Composable
fun JawabanSalah(
    onLanjutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5A5A), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            // 🥦 Gambar karakter di kiri
            Image(
                painter = painterResource(id = R.drawable.brokolisalah), // ganti sesuai gambar kamu
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 10.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Yah, Jawabanmu Salah!",
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Lebih cermat lagi ya~",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onLanjutClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6EC8FF)
                    ),
                    modifier = Modifier
                        .height(45.dp)
                        .shadow(4.dp, RoundedCornerShape(12.dp))
                ) {
                    Text(text = "LANJUT", fontSize = 16.sp)
                }
            }
        }
    }
}

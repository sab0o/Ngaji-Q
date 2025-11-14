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
fun JawabanBenar(
    onLanjutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFD8F9D4), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            // 🥦 Gambar karakter di kiri
            Image(
                painter = painterResource(id = R.drawable.brokolibenar), // ganti sesuai gambar kamu
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
                    text = "Jawabanmu Benar!",
                    fontSize = 20.sp,
                    color = Color(0xFF056839)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Keren! Pemahamanmu di soal ini sudah bagus!",
                    fontSize = 14.sp,
                    color = Color(0xFF056839)
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

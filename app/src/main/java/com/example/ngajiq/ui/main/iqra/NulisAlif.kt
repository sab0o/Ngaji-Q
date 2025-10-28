package com.example.ngajiq.ui.iqra

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.ngajiq.ui.theme.NgajiQTheme

@Composable
fun NulisAlifScreen(navController: NavController) {
    var dragOffset by remember { mutableFloatStateOf(0f) }
    val maxDrag = 500f
    val progress = (dragOffset / maxDrag).coerceIn(0f, 1f)
    val isComplete = progress >= 1f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F9FF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header sederhana
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "#2 Alif (Tulis)", fontSize = 18.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        // Balon instruksi
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(text = "Yuk tulis hurufnya!", fontSize = 16.sp, color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Area menulis (Alif)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            // Garis panduan vertikal
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE0F2FF))
            )

            // Progress warna biru naik dari bawah
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height((300 * progress).dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF86D1FF), Color(0xFF42BBE4))
                        )
                    )
            )

            // Tombol drag
            Box(
                modifier = Modifier
                    .offset(y = (300 * progress).dp - 30.dp)
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF86D1FF))
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            dragOffset = (dragOffset + dragAmount.y).coerceIn(0f, maxDrag)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("⬇️", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Tombol lanjut
        Button(
            onClick = {
                navController.navigate("listenIqra")
            },
            enabled = isComplete,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isComplete) Color(0xFF60A5FA) else Color(0xFFE5E7EB)
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp)
        ) {
            Text(
                text = "LANJUT",
                color = if (isComplete) Color.White else Color.Gray,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NulisAlifScreenPreview() {
    NgajiQTheme {
        val navController = rememberNavController()
        NulisAlifScreen(navController)
    }
}

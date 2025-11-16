package com.example.ngajiq.ui.iqra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ngajiq.R
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlifBacaJawabScreen(navController: NavController) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    val correctAnswer = "I" // jawaban benar (contoh huruf: Ba)

    // Warna background hasil jawaban
    val backgroundColor = when {
        showResult && isCorrect -> Color(0xFFD5F8C6) // hijau muda
        showResult && !isCorrect -> Color(0xFFFFD6D6) // merah muda
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {

            // === BAGIAN ATAS: Progress + Tombol pause ===
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                progress = { 0.4f },
                modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .height(8.dp),
                color = Color(0xFF87CEFA),
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
                IconButton(onClick = { /* TODO: Pause action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pause),
                        contentDescription = "Pause",
                        tint = Color(0xFF87CEFA)
                    )
                }
            }

            // === PERTANYAAN ===
            Text(
                text = "Huruf apakah ini?",
                fontSize = 20.sp,
                color = Color(0xFF555555),
                modifier = Modifier.padding(top = 16.dp)
            )

            // === HURUF ARAB ===
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color(0xFFEAF6FF), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.i),
                    contentDescription = "Huruf i",
                    modifier = Modifier
                        .size(150.dp) // ubah ukuran sesuai kebutuhan
                )
            }

            // === PILIHAN JAWABAN ===
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                listOf("A", "I", "U").forEach { option ->
                    Button(
                        onClick = { selectedAnswer = option },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedAnswer == option)
                                Color(0xFFB0E0E6) else Color(0xFFF1F1F1),
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.size(70.dp)
                    ) {
                        Text(option, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // === HASIL JAWABAN / TOMBOL PERIKSA ===
            if (!showResult) {
                Button(
                    onClick = {
                        if (selectedAnswer != null) {
                            showResult = true
                            isCorrect = selectedAnswer == correctAnswer
                        }
                    },
                    enabled = selectedAnswer != null,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text("Periksa Jawaban", fontSize = 18.sp)
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Gambar Brokoli (Benar/Salah)
                    Image(
                        painter = painterResource(
                            id = if (isCorrect) R.drawable.brokolibenar else R.drawable.brokolisalah
                        ),
                        contentDescription = "Brokoli Reaction",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(bottom = 8.dp)
                    )

                    // Teks hasil jawaban
                    Text(
                        text = if (isCorrect) "Jawabanmu Benar!" else "Yah, Jawabanmu Salah!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isCorrect) Color(0xFF388E3C) else Color(0xFFD32F2F)
                    )

                    Text(
                        text = if (isCorrect)
                            "Keren! Pemahamanmu di soal ini sudah bagus!"
                        else
                            "Lebih cermat lagi ya~",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { navController.navigate("flashcard") },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEFA)),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text("LANJUT", fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAlifBacaJawabScreen() {
    // Gunakan tema aplikasi biar warna dan font sesuai
    com.example.ngajiq.ui.theme.NgajiQTheme {
        // Dummy navController agar tidak error
        val navController = androidx.navigation.compose.rememberNavController()
        AlifBacaJawabScreen(navController = navController)
    }
}

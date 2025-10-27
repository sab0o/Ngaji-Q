package com.example.ngajiq.ui.main.iqra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R
import com.example.ngajiq.ui.theme.NgajiQTheme

class SpellingAlifScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NgajiQTheme {
                SpellingScreen()
            }
        }
    }
}

// 🔹 KOMPONEN UTAMA LAYAR SPELLING
@Composable
fun SpellingScreen() {
    // State untuk melacak apakah audio sudah diputar atau belum
    var isAudioPlayed by remember { mutableStateOf(false) }

    // Warna untuk status tombol Lanjut
    val disabledButtonColor = Color(0xFFE5E7EB)
    val activeButtonColor = Color(0xFF60A5FA)
    val activeTextColor = Color.White
    val disabledTextColor = Color(0xFF9CA3AF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F9FF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Bagian Header ---
        SpellingHeader(progress = 0.1f)
        Spacer(modifier = Modifier.height(16.dp))



        // --- Balon instruksi ---
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(text = "Dengarkan bunyi hurufnya!", fontSize = 16.sp, color = Color.DarkGray)
        }
        Spacer(modifier = Modifier.height(32.dp))

        // --- Tampilan Huruf Utama ---
        MainLetterDisplay(
            onSpeakerClick = {
                // TODO: Tambahkan logika untuk memutar audio di sini
                isAudioPlayed = true // Mengubah state saat speaker ditekan
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        // --- Ejaan Huruf ---
        LetterSpelling(letters = listOf("A", "L", "I", "F"))

        Spacer(modifier = Modifier.weight(1f)) // Mendorong tombol ke bawah

        // --- Tombol Lanjut ---
        Button(
            onClick = { /* TODO: Logika lanjut ke layar berikutnya */ },
            enabled = isAudioPlayed, // Tombol aktif jika audio sudah diputar
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = activeButtonColor,
                disabledContainerColor = disabledButtonColor
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
        ) {
            Text(
                text = "LANJUT",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isAudioPlayed) activeTextColor else disabledTextColor
            )
        }
    }
}

// 🔹 Header (Profil, Progress, Pause)
@Composable
fun SpellingHeader(
    progress: Float = 0.3f,
    onPauseClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto Profil (placeholder)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF86EFAC), CircleShape), // Warna diubah agar sesuai gambar
                contentAlignment = Alignment.Center
            ) {
                // Placeholder untuk gambar profil, bisa diganti Image
                Text("🥦", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Progress bar
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .background(Color(0xFFBFDBFE), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .background(Color(0xFF60A5FA), RoundedCornerShape(10.dp))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Tombol pause
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFBFDBFE), CircleShape)
                    .clickable { onPauseClick() },
                contentAlignment = Alignment.Center
            ) {
                Text("⏸️", fontSize = 18.sp)
            }
        }
    }
}

// 🔹 Tampilan Huruf Utama dengan Tombol Speaker
@Composable
fun MainLetterDisplay(onSpeakerClick: () -> Unit) {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 15f), 0f)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Tombol Speaker
        IconButton(
            onClick = onSpeakerClick,
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFFBFDBFE), RoundedCornerShape(12.dp))
        ) {
//            Icon(
//                Icons.Filled.Notifications,
//                contentDescription = "Dengarkan Audio",
//                tint = Color(0xFF60A5FA)
//            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Box dengan border putus-putus
        Box(
            modifier = Modifier
                .weight(1f)
                .height(250.dp)
                .drawBehind {
                    drawRoundRect(
                        color = Color(0xFF42BBE4),
                        style = stroke,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.alif), // Ganti dengan nama file gambar Anda
                contentDescription = "Alif",
                modifier = Modifier.size(130.dp)  // Atur ukuran gambar
            )
        }
    }
}

// 🔹 Komponen untuk Ejaan Huruf per Kotak
@Composable
fun LetterSpelling(letters: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        letters.forEach { letter ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(60.dp)
                    .border(
                        BorderStroke(2.dp, Color(0xFF42BBE4)),
                        RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = letter,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF353535)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SpellingScreenPreview() {
    NgajiQTheme {
        SpellingScreen()
    }
}
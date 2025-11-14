package com.example.ngajiq.ui.main.iqra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image // DITAMBAHKAN: Import untuk Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource // DITAMBAHKAN: Import untuk painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign // DITAMBAHKAN: Import untuk text alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R
import com.example.ngajiq.ui.theme.NgajiQTheme

class FlashcardAlif : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NgajiQTheme {
                FlashcardScreen()
            }
        }
    }
}

// 🔹 HEADER: profil, progress, pause, label Flashcard (Tidak ada perubahan)
@Composable
fun FlashcardHeader(
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

        Spacer(modifier = Modifier.height(16.dp))

        // Label Flashcard
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Flashcard",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}


// 🔹 HALAMAN FLASHCARD (DIUBAH)
@Composable
fun FlashcardScreen() {
    var showDetail by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F9FF)) // Menambahkan warna background
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header di atas
        FlashcardHeader(progress = 0.1f, onPauseClick = { /* keluar halaman */ })

        Spacer(modifier = Modifier.weight(1f)) // Spacer agar kartu lebih ke tengah

        // Kartu Flashcard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Ukuran kartu disesuaikan
                .background(
                    color = if (showDetail) Color(0xFF297AF3) else Color(0xFFD2F4FF),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable { showDetail = !showDetail },
            contentAlignment = Alignment.Center
        ) {
            // DIUBAH: Konten kartu disesuaikan dengan gambar
            if (showDetail) {
                // Tampilan belakang kartu (dengan detail)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Menggunakan Image untuk menampilkan Alif Fathah
                    Image(
                        painter = painterResource(id = R.drawable.aliffathah), // Ganti dengan nama file gambar Anda
                        contentDescription = "Alif Fathah",
                        modifier = Modifier.size(130.dp)  // Atur ukuran gambar
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Fathah letaknya\ndi atas huruf",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Dibaca : A",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                // Tampilan depan kartu
                Text(
                    text = "Fathah",
                    fontSize = 32.sp,
                    color = Color(0xFF353535),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // Spacer agar tombol lebih ke bawah

        // Tombol Lanjut
        Button(
            onClick = { /* TODO: Logika untuk lanjut ke kartu berikutnya */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60A5FA)),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
        ) {
            Text(
                text = "LANJUT",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashcardPreview() {
    NgajiQTheme {
        FlashcardScreen()
    }
}
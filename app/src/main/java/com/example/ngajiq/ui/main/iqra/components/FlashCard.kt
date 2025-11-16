package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * FlashcardCard:
 * - menampilkan "depan" (title) dulu
 * - saat diklik: berubah ke "belakang" (detail) -> memanggil onShowDetail()
 * - resetKey: ketika berubah (index berubah) maka card akan kembali ke state depan
 */
@Composable
fun FlashcardCard(
    title: String,
    description: String,
    readAs: String,
    imageRes: Int,
    onShowDetail: () -> Unit,   // dipanggil saat card dibalik (menampilkan detail)
    resetKey: Int,             // kirim currentIndex supaya card reset saat pindah
    modifier: Modifier = Modifier
) {
    var showDetail by remember { mutableStateOf(false) }

    // Jika resetKey berubah, kembali ke tampilan depan
    LaunchedEffect(resetKey) {
        showDetail = false
    }

    Box(
        modifier = modifier
            .height(360.dp)
            .background(
                color = if (showDetail) Color(0xFF297AF3) else Color(0xFFD2F4FF),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
                // hanya trigger ketika dari depan -> belakang
                if (!showDetail) {
                    showDetail = true
                    onShowDetail()
                }
            }
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        if (showDetail) {
            // Belakang kartu: gambar + penjelasan
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.size(140.dp)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = description,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Dibaca : $readAs",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            // Depan kartu: hanya judul
            Text(
                text = title,
                fontSize = 32.sp,
                color = Color(0xFF353535),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.ui.main.iqra.components.ButtonBlue
import com.example.ngajiq.ui.main.iqra.components.FlashcardCard
import com.example.ngajiq.ui.main.iqra.components.HintText
import com.example.ngajiq.ui.main.iqra.components.ProgressBar
import com.example.ngajiq.ui.theme.*
import com.example.ngajiq.data.repository.FlashcardRepository
import com.example.ngajiq.data.repository.FlashcardData
import com.example.ngajiq.ui.navigation.Routes

@Composable
fun FlashcardScreen(navController: NavController) {

    // index kartu saat ini
    var currentIndex by remember { mutableStateOf(0) }

    // apakah detail kartu saat ini sudah ditampilkan (user sudah menekan card)
    var isDetailShown by remember { mutableStateOf(false) }

    // ambil daftar kartu
    val flashcards = FlashcardRepository.flashcards
    val current: FlashcardData = flashcards[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Progress bar (menampilkan progress sesuai index)
        ProgressBar(
            progress = (currentIndex + 1f) / flashcards.size,
            onPauseClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        HintText(
            text = "Flashcard",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        // FLASHCARD: tampilkan title, klik -> tampilkan detail (panggil onShowDetail)
        FlashcardCard(
            title = current.title,
            description = current.description,
            readAs = current.readAs,
            imageRes = current.imageRes,
            onShowDetail = {
                // ketika user menekan card dan detail muncul, aktifkan tombol lanjut
                isDetailShown = true
            },
            resetKey = currentIndex, // supaya card reset tiap pindah index
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Tombol LANJUT: aktif hanya jika isDetailShown == true
        ButtonBlue(
            text = if (currentIndex == flashcards.lastIndex) "SELESAI" else "LANJUT",
            onClick = {
                if (isDetailShown) {
                    if (currentIndex < flashcards.lastIndex) {
                        currentIndex++
                        // pindah ke kartu berikutnya dan reset isDetailShown agar tombol disable
                        isDetailShown = false
                    } else {
                        // sudah di kartu terakhir; arahkan sesuai alur aplikasi (mis. ke alif_baca_jawab)
                        navController.navigate(Routes.LEARNINGCOMPLETION)
                    }
                }
            },
            buttonColor = if (isDetailShown) PrimaryBlue else Color(0xFFE5E7EB),
            shadowColor = if (isDetailShown) DeepBlue else Color(0xFFB0BEC5),
            textColor = if (isDetailShown) IceBlue else Color(0xFF9CA3AF),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFlashcardScreen() {
    NgajiQTheme {
        FlashcardScreen(navController = rememberNavController())
    }
}

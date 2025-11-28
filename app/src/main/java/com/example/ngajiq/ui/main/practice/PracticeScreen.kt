package com.example.ngajiq.ui.main.practice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.data.PracticeRepository
import com.example.ngajiq.ui.main.iqra.components.ProgressBar
import com.example.ngajiq.ui.theme.BrightCyan
import com.example.ngajiq.ui.theme.BrightCyanShadow

// Warna Glow (Pastikan sesuai tema)
val GlowBlue = Color(0xFF80D8FF)

@Composable
fun PracticeScreen(
    repo: PracticeRepository,
    category: String,
    practiceId: Int?,
) {
    if (practiceId == null) return
    val practice = repo.getPracticeById(category, practiceId) ?: return

    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isAnswered by remember { mutableStateOf(false) }

    val question = practice.questions.getOrNull(currentIndex) ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Bar
        ProgressBar(
            progress = (currentIndex + 1) / practice.questions.size.toFloat(),
            onPauseClick = { }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- KOMPONEN KARTU NOMOR (Single) ---
        SingleGlowingCard(text = "Nomor ${currentIndex + 1}")
        // -------------------------------------

        Spacer(modifier = Modifier.height(24.dp))

        // Soal
        Text(
            text = question.question ?: "-",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Pilihan Jawaban
        question.answers.forEach { answer ->
            OutlinedButton(
                onClick = {
                    if (!isAnswered) {
                        selectedAnswer = answer
                        isAnswered = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 6.dp)
                    .height(50.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = when {
                        isAnswered && answer == question.correctAnswer -> Color.Red
                        isAnswered && answer == selectedAnswer -> BrightCyan
                        else -> BrightCyanShadow
                    }
                ),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        isAnswered && answer == question.correctAnswer -> Color.Red
                        isAnswered && answer == selectedAnswer -> BrightCyan
                        else -> Color.White
                    }
                )
            ) {
                val textColor = if (isAnswered && (answer == question.correctAnswer || answer == selectedAnswer)) Color.White else Color.Black
                Text(answer, color = textColor, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tombol Next
        if (isAnswered) {
            Button(
                onClick = {
                    if (currentIndex < practice.questions.size - 1) {
                        currentIndex++
                        selectedAnswer = null
                        isAnswered = false
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (currentIndex < practice.questions.size - 1) "Next" else "Finish")
            }
        }
    }
}

// --- Komponen Kartu Tunggal ---
@Composable
fun SingleGlowingCard(text: String) {
    Box(contentAlignment = Alignment.Center) {
        val cardShape = RoundedCornerShape(percent = 50)

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(60.dp)
                .clip(cardShape)
                .blur(radius = 24.dp)
                .background(
                    color = GlowBlue.copy(alpha = 0.5f),
                    shape = cardShape
                )
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(45.dp),
            shape = cardShape,
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Warna teks Biru Cyan
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PracticeScreenPreview() {
    val repo = PracticeRepository
    PracticeScreen(repo, category = "Fiqih", practiceId = 1)
}
package com.example.ngajiq.ui.main.practice

import androidx.compose.runtime.Composable
import com.example.ngajiq.data.model.Practice
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ngajiq.data.PracticeRepository
import com.example.ngajiq.data.model.Question

@Composable
fun PracticeScreen(
    repo: PracticeRepository,
    category: String,
    practiceId: Int?,   // ← buat nullable
) {

    if (practiceId == null) {
        Text("Practice ID tidak ditemukan", modifier = Modifier.padding(20.dp))
        return
    }

    val practice = repo.getPracticeById(category,practiceId)

    if (practice == null) {
        Text("Practice tidak tersedia ${category} ${practiceId}", modifier = Modifier.padding(20.dp))
        return
    }

    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isAnswered by remember { mutableStateOf(false) }

    val question = practice.questions.getOrNull(currentIndex)

    if (question == null) {
        Text("Soal tidak ditemukan", modifier = Modifier.padding(20.dp))
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = practice.name ?: "Practice",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = question.question ?: "-",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        question.answers.forEach { answer ->
            Button(
                onClick = {
                    if (!isAnswered) {
                        selectedAnswer = answer
                        isAnswered = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        when {
                            isAnswered && answer == question.correctAnswer -> MaterialTheme.colorScheme.primary
                            isAnswered && answer == selectedAnswer -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.secondary
                        }
                )
            ) {
                Text(answer)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

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
                Text(
                    if (currentIndex < practice.questions.size - 1) "Next"
                    else "Finish"
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PracticeScreenPreview() {
    var repo  = PracticeRepository
    PracticeScreen(
        repo,
        category="Fiqih",
        practiceId = 1
    )
}

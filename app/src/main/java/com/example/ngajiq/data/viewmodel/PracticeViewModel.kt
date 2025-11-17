package com.example.ngajiq.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.ngajiq.data.PracticeRepository
import com.example.ngajiq.data.model.Question
import com.example.ngajiq.data.model.Practice

class PracticeViewModel(
    category: String = "Fiqih",
    practiceId: Int = 1
) : ViewModel() {

    private val practice: Practice? = PracticeRepository.getPracticeById(category,practiceId)
    private val questions = practice?.questions ?: emptyList()

    var currentIndex by mutableStateOf(0)
        private set

    var selectedAnswer by mutableStateOf<String?>(null)
        private set

    var score by mutableStateOf(0)
        private set

    val currentQuestion: Question
        get() = questions[currentIndex]

    fun selectAnswer(answer: String) {
        selectedAnswer = answer
    }

    fun nextQuestion() {
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
        }

        if (currentIndex < questions.lastIndex) {
            currentIndex++
            selectedAnswer = null
        }
    }

    fun isFinished(): Boolean =
        currentIndex == questions.lastIndex && selectedAnswer != null
}

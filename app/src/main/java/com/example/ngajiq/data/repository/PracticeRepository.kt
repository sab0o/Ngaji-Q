package com.example.ngajiq.data

import com.example.ngajiq.data.model.Practice
import com.example.ngajiq.data.model.Question

object PracticeRepository {

    private val fiqihPractices = listOf(
        Practice(
            id = 1,
            name = "Latihan Wudhu Dasar",
            questions = listOf(
                Question(
                    question = "Ada berapa rukun wudhu?",
                    answers = listOf("3", "4", "5", "6"),
                    correctAnswer = "6"
                ),
                Question(
                    question = "Yang tidak termasuk rukun wudu adalah...",
                    answers = listOf("Niat", "Membasuh wajah", "Mengusap telinga", "Membasuh kedua kaki"),
                    correctAnswer = "Mengusap telinga"
                ),
                Question(
                    question = "Wudhu bisa batal kalau...",
                    answers = listOf("Menonton televisi", "Membantu orang tua", "Buang air kecil", "Berjalan ke masjid"),
                    correctAnswer = "Buang air kecil"
                ),
                Question(
                    question = "Berapa kali anggota tubuh harus dibasuh ...",
                    answers = listOf("1 kali", "2 kali", "3 kali", "4 kali"),
                    correctAnswer = "1 kali"
                ),
                Question(
                    question = "Yang termasuk sunah wudu adalah...",
                    answers = listOf("Bersiwak", "Membasuh wajah", "Membasuh kedua tangan", "Tertib"),
                    correctAnswer = "Bersiwak"
                )
            )
        )
    )

    private val tauhidPractices = emptyList<Practice>()
    private val akhlakPractices = emptyList<Practice>()
    private val sejarahPractices = emptyList<Practice>()
    private val doaPractices = emptyList<Practice>()
    private val hadistPractices = emptyList<Practice>()

    fun getPracticeByCategory(category: String): List<Practice> {
        return when (category) {
            "Fiqih" -> fiqihPractices
            "Tauhid" -> tauhidPractices
            "Akhlak" -> akhlakPractices
            "Sejarah" -> sejarahPractices
            "Doa" -> doaPractices
            "Hadist" -> hadistPractices
            else -> emptyList()
        }
    }

    fun getPracticeById(category: String, id: Int): Practice? {
        return getPracticeByCategory(category).find { it.id == id }
    }
}
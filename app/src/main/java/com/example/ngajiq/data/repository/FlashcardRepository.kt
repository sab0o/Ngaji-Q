package com.example.ngajiq.data.repository

import com.example.ngajiq.R

object FlashcardRepository {
    val flashcards = listOf(
        FlashcardData(
            title = "Fathah",
            description = "Fathah berada di atas huruf",
            readAs = "A",
            imageRes = R.drawable.aliffathah
        ),
        FlashcardData(
            title = "Kasrah",
            description = "Kasrah berada di bawah huruf",
            readAs = "I",
            imageRes = R.drawable.alifkasrah
        ),
        FlashcardData(
            title = "Dhammah",
            description = "Dhammah berada di atas dan bentuknya seperti huruf waw",
            readAs = "U",
            imageRes = R.drawable.alifdhammah
        )
    )
}

data class FlashcardData(
    val title: String,
    val description: String,
    val readAs: String,
    val imageRes: Int
)

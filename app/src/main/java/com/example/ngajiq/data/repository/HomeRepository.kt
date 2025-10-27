package com.example.ngajiq.data.repository
import androidx.compose.ui.graphics.Color
import com.example.ngajiq.data.model.Category
import com.example.ngajiq.data.model.Recommendation


object HomeRepository {

    fun getCategories(): List<Category> {
        return listOf(
            Category(1, "Akhlak", 0, Color(0xFFFEDBD0)),
            Category(2, "Fiqih", 0, Color(0xFFB0E0E6)),
            Category(3, "Kisah Nabi", 0, Color(0xFFFFDAB9)),
            Category(4, "Sains", 0, Color(0xFFD4F0D3)),
            Category(5, "Sejarah", 0, Color(0xFFFBE4C4)),
            Category(6, "Tauhid", 0, Color(0xFFE6E6FA))
        )
    }

    fun getRecommendations(): List<Recommendation> {
        return listOf(
            Recommendation(1, "Belajar Wudhu' Materi 01", "10:30", 0), // Placeholder
            Recommendation(2, "Shalat Sunnah Tahajjud", "12:05", 0),  // Placeholder
            Recommendation(3, "Cara Berpuasa yang Benar", "08:45", 0),  // Placeholder
        )
    }

    fun getFeaturedCard(): Recommendation {
        return Recommendation(
            id = 99,
            title = "Bagaimana Allah SWT membuat Alam Semesta?",
            duration = "",
            imageResId = 0 // Placeholder
        )
    }
}
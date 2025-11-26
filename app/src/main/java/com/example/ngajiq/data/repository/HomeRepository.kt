package com.example.ngajiq.data.repository

import androidx.compose.ui.graphics.Color
import com.example.ngajiq.R
import com.example.ngajiq.data.model.Category
import com.example.ngajiq.data.model.Recommendation
import com.example.ngajiq.data.model.Subject
import com.example.ngajiq.data.repository.SubjectRepository.getRandomSubjects

object HomeRepository {

    fun getCategories(): List<Category> {
        return listOf(
            Category(1, "Akhlak", R.drawable.home1, Color(0xFFFEDBD0)),
            Category(2, "Fiqih", R.drawable.home2, Color(0xFFB0E0E6)),
            Category(3, "Kisah Nabi", R.drawable.home3, Color(0xFFFFDAB9)),
            Category(4, "Sains", R.drawable.home4, Color(0xFFD4F0D3)),
            Category(5, "Sejarah", R.drawable.home5, Color(0xFFFBE4C4)),
            Category(6, "Tauhid", R.drawable.home6, Color(0xFFE6E6FA))
        )
    }

    fun getRecommendations(): List<Subject> {
        return getRandomSubjects(limit=5)
    }

    fun getFeaturedCard(): Recommendation {
        return Recommendation(
            id = 99,
            title = "Bagaimana Allah SWT membuat Alam Semesta?",
            duration = "25:00",
            imageResId = R.drawable.featured,
            progress = 0.9f
        )
    }
}

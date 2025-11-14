package com.example.ngajiq.data.repository

import androidx.compose.ui.graphics.Color
import com.example.ngajiq.R
import com.example.ngajiq.data.model.Category
import com.example.ngajiq.data.model.Recommendation

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

    fun getRecommendations(): List<Recommendation> {
        return listOf(
            // These MUST not use home1..home6 (you asked). Use different drawables in res/drawable.
            Recommendation(1, "Belajar Wudhu' Materi 01", "10:30", R.drawable.rec1, 0.5f),
            Recommendation(2, "Shalat Sunnah Tahajjud", "12:05", R.drawable.rec2, 0.8f),
            Recommendation(3, "Cara Berpuasa yang Benar", "08:45", R.drawable.rec3, 0.3f)
        )
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

package com.example.ngajiq.data.model

import androidx.compose.ui.graphics.Color

data class Category(
    val id: Int,
    val name: String,
    val iconId: Int = 0,
    val iconColor: Color
)

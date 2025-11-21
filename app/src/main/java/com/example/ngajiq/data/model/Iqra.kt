package com.example.ngajiq.data.model

data class Iqra(
    val id: Int,
    val title: String,
    val totalLevel: Int = 30,
    val currentLevel: Int = 0,
    val lockedState: Boolean = true,
)

package com.example.ngajiq.data.model


data class Video(
    val id: Int,
    val title: String,
    val thumbnailRes: Int, // Resource ID for the thumbnail image
    val category: String, // <--- ADD THIS LINE
    val duration: String = "10:30" // Optional: for the duration overlay
)
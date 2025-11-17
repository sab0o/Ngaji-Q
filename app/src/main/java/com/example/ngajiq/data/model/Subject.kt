package com.example.ngajiq.data.model

import com.example.ngajiq.data.PracticeRepository


data class Subject(
    val id: Int,
    val title: String,
    val thumbnailRes: Int, // Resource ID for the thumbnail image
    val category: String, // <--- ADD THIS LINE
    val duration: String = "10:30", // Optional: for the duration overlay
    val description: String = "Wudhu adalah salah satu syarat sah salat. Wudhu adalah mensucikan anggota badan tertentu dengan air untuk menghilangkan hadas kecil. Hadas kecil adalah keadaan seseorang yang tidak suci karena keluarnya sesuatu dari qubul atau dubur, atau karena hilang akal",
    val timeline: List<Time> = listOf(Time("Pengertian Wudhu","00:01"),Time("Rukun Wudhu","03:35"),Time("Sunnah Wudhu", "08:14"),Time("Tata Cara Wudhu","13:21")),
    val practice: Practice? = PracticeRepository.getPracticeById("Fiqih", 1)
)

data class Time(
    val label: String,
    val timeStamp: String,
)
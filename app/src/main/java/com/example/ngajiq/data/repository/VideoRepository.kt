package com.example.ngajiq.data.repository

import com.example.ngajiq.R
import com.example.ngajiq.data.model.Video

object LocalVideoRepository {
    val categories = listOf("Fiqih", "Tauhid", "Akhlak", "Sejarah", "Doa", "Hadist")

    val videos = listOf(
        // Fiqih Category (Belajar Wudhu)
        Video(
            id = 1,
            title = "Belajar Wudhu Materi 01",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Fiqih",
            duration = "10:30"
        ),
        Video(
            id = 2,
            title = "Belajar Wudhu Materi 02",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Fiqih",
            duration = "10:30"
        ),
        Video(
            id = 3,
            title = "Belajar Wudhu Materi 03",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Fiqih",
            duration = "10:30"
        ),
        Video(
            id = 4,
            title = "Belajar Wudhu Materi 04",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Fiqih",
            duration = "10:30"
        ),
        Video(
            id = 5,
            title = "Belajar Wudhu Materi 05",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Fiqih",
            duration = "10:30"
        ),

        // Tauhid Category (Asmaul Husna)
        Video(
            id = 6,
            title = "Asmaul Husna Bagian 1",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Tauhid",
            duration = "05:30"
        ),
        Video(
            id = 7,
            title = "Asmaul Husna Bagian 2",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Tauhid",
            duration = "07:42"
        ),
        Video(
            id = 8,
            title = "Asmaul Husna Bagian 3",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Tauhid",
            duration = "08:02"
        ),
        Video(
            id = 9,
            title = "Asmaul Husna Bagian 4",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Tauhid",
            duration = "10:30"
        ),

        // Akhlak Category (Kisah Teladan Ruqayyah)
        Video(
            id = 10,
            title = "Kisah Teladan Ruqayyah",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Akhlak",
            duration = "10:30"
        ),
        Video(
            id = 11,
            title = "Kisah Teladan Ruqayyah",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Akhlak",
            duration = "10:30"
        ),
        Video(
            id = 12,
            title = "Kisah Teladan Ruqayyah",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Akhlak",
            duration = "10:30"
        ),

        // Sejarah Category (Islam pada Masa Bani Umayyah, Kisah Semut)
        Video(
            id = 13,
            title = "Islam Pada Masa Bani Ummayyah",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Sejarah",
            duration = "10:30"
        ),
        Video(
            id = 14,
            title = "Kisah Semut dan Nabi Sulaiman a.s",
            thumbnailRes = R.drawable.thumbnail_wudhu,
            category = "Sejarah",
            duration = "10:30"
        )
    )
}

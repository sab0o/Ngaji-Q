package com.example.ngajiq.data.repository

import com.example.ngajiq.R
import com.example.ngajiq.data.model.Video
import com.example.ngajiq.data.repository.LocalVideoRepository.akhlakVideos
import com.example.ngajiq.data.repository.LocalVideoRepository.doaVideos
import com.example.ngajiq.data.repository.LocalVideoRepository.fiqihVideos
import com.example.ngajiq.data.repository.LocalVideoRepository.hadistVideos
import com.example.ngajiq.data.repository.LocalVideoRepository.sejarahVideos
import com.example.ngajiq.data.repository.LocalVideoRepository.tauhidVideos

object LocalVideoRepository {

    val categories = listOf("Fiqih", "Tauhid", "Akhlak", "Sejarah", "Doa", "Hadist")

    // ---------------------------
    // FIQIH VIDEOS (ID 1..5)
    // ---------------------------
    val fiqihVideos = listOf(
        Video(1, "Belajar Wudhu Materi 01", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Video(2, "Belajar Wudhu Materi 02", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Video(3, "Belajar Wudhu Materi 03", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Video(4, "Belajar Wudhu Materi 04", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Video(5, "Belajar Wudhu Materi 05", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
    )

    // ---------------------------
    // TAUHID VIDEOS (ID 1..4)
    // ---------------------------
    val tauhidVideos = listOf(
        Video(1, "Asmaul Husna Bagian 1", R.drawable.thumbnail_wudhu, "Tauhid", "05:30"),
        Video(2, "Asmaul Husna Bagian 2", R.drawable.thumbnail_wudhu, "Tauhid", "07:42"),
        Video(3, "Asmaul Husna Bagian 3", R.drawable.thumbnail_wudhu, "Tauhid", "08:02"),
        Video(4, "Asmaul Husna Bagian 4", R.drawable.thumbnail_wudhu, "Tauhid", "10:30"),
    )

    // ---------------------------
    // AKHLAK VIDEOS (ID 1..3)
    // ---------------------------
    val akhlakVideos = listOf(
        Video(1, "Kisah Teladan Ruqayyah", R.drawable.thumbnail_wudhu, "Akhlak", "10:30"),
        Video(2, "Kisah Teladan Ruqayyah", R.drawable.thumbnail_wudhu, "Akhlak", "10:30"),
        Video(3, "Kisah Teladan Ruqayyah", R.drawable.thumbnail_wudhu, "Akhlak", "10:30"),
    )

    // ---------------------------
    // SEJARAH VIDEOS (ID 1..2)
    // ---------------------------
    val sejarahVideos = listOf(
        Video(1, "Islam Pada Masa Bani Ummayyah", R.drawable.thumbnail_wudhu, "Sejarah", "10:30"),
        Video(2, "Kisah Semut dan Nabi Sulaiman a.s", R.drawable.thumbnail_wudhu, "Sejarah", "10:30"),
    )

    // ---------------------------
    // EMPTY FOR FUTURE CONTENT
    // ---------------------------
    val doaVideos = emptyList<Video>()
    val hadistVideos = emptyList<Video>()

    // ---------------------------
    // OPTIONAL: COMBINED LIST
    // ---------------------------
    val videos = fiqihVideos + tauhidVideos + akhlakVideos + sejarahVideos
    fun getVideosByCategory(category: String): List<Video> {
        return when (category) {
            "Fiqih" -> fiqihVideos
            "Tauhid" -> tauhidVideos
            "Akhlak" -> akhlakVideos
            "Sejarah" -> sejarahVideos
            "Doa" -> doaVideos
            "Hadist" -> hadistVideos
            else -> emptyList()
        }
    }
}



package com.example.ngajiq.data.repository
import com.example.ngajiq.R
import com.example.ngajiq.data.model.Video

object LocalVideoRepository {
    val videos = listOf(
        Video(
            id = 1,
            title = "Belajar Wudhu Materi 1",
            thumbnailRes = R.drawable.thumbnail_wudhu,
        ),
        Video(
            id = 2,
            title = "Belajar Wudhu Materi 2",
            thumbnailRes = R.drawable.thumbnail_wudhu,
        ),
        Video(
            id = 3,
            title = "Belajar Wudhu Materi 3",
            thumbnailRes = R.drawable.thumbnail_wudhu,
        )
    )
}
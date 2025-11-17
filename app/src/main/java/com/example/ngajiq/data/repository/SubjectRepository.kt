package com.example.ngajiq.data.repository

import com.example.ngajiq.R
import com.example.ngajiq.data.model.Subject

object SubjectRepository {

    val categories = listOf("Fiqih", "Tauhid", "Akhlak", "Sejarah", "Doa", "Hadist")

    // ---------------------------
    // FIQIH Subjects (ID 1..5)
    // ---------------------------
    val fiqihSubjects = listOf(
        Subject(1, "Belajar Wudhu Materi 01", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Subject(2, "Belajar Wudhu Materi 02", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Subject(3, "Belajar Wudhu Materi 03", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Subject(4, "Belajar Wudhu Materi 04", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
        Subject(5, "Belajar Wudhu Materi 05", R.drawable.thumbnail_wudhu, "Fiqih", "10:30"),
    )

    // ---------------------------
    // TAUHID Subjects (ID 1..4)
    // ---------------------------
    val tauhidSubjects = listOf(
        Subject(1, "Asmaul Husna Bagian 1", R.drawable.thumbnail_tauhid, "Tauhid", "05:30"),
        Subject(2, "Asmaul Husna Bagian 2", R.drawable.thumbnail_tauhid, "Tauhid", "07:42"),
        Subject(3, "Asmaul Husna Bagian 3", R.drawable.thumbnail_tauhid, "Tauhid", "08:02"),
        Subject(4, "Asmaul Husna Bagian 4", R.drawable.thumbnail_tauhid, "Tauhid", "10:30"),
    )

    // ---------------------------
    // AKHLAK Subjects (ID 1..3)
    // ---------------------------
    val akhlakSubjects = listOf(
        Subject(1, "Kisah Teladan Ruqayyah", R.drawable.thumbnail_akhlak, "Akhlak", "10:30"),
        Subject(2, "Kisah Teladan Ruqayyah", R.drawable.thumbnail_akhlak, "Akhlak", "10:30"),
        Subject(3, "Kisah Teladan Ruqayyah", R.drawable.thumbnail_akhlak, "Akhlak", "10:30"),
    )

    // ---------------------------
    // SEJARAH Subjects (ID 1..2)
    // ---------------------------
    val sejarahSubjects = listOf(
        Subject(1, "Islam Pada Masa Bani Ummayyah", R.drawable.thumbnail_sejarah, "Sejarah", "10:30"),
        Subject(2, "Kisah Semut dan Nabi Sulaiman a.s", R.drawable.thumbnail_sejarah, "Sejarah", "10:30"),
    )

    // ---------------------------
    // EMPTY FOR FUTURE CONTENT
    // ---------------------------
    val doaSubjects = emptyList<Subject>()
    val hadistSubjects = emptyList<Subject>()

    // ---------------------------
    // OPTIONAL: COMBINED LIST
    // ---------------------------
    val subjects = fiqihSubjects + tauhidSubjects + akhlakSubjects + sejarahSubjects
    fun getSubjectsByCategory(category: String): List<Subject> {
        return when (category) {
            "Fiqih" -> fiqihSubjects
            "Tauhid" -> tauhidSubjects
            "Akhlak" -> akhlakSubjects
            "Sejarah" -> sejarahSubjects
            "Doa" -> doaSubjects
            "Hadist" -> hadistSubjects
            else -> emptyList()
        }
    }
    fun getSubjectFromCategory(category: String, subjectId: Int?): Subject? {
        return getSubjectsByCategory(category).find { it.id == subjectId }
    }
}



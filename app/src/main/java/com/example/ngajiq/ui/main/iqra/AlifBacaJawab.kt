package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.ui.main.iqra.components.ButtonLanjut
import com.example.ngajiq.ui.main.iqra.components.DashedLetterBox
import com.example.ngajiq.ui.main.iqra.components.HintTextWithSpeaker
import com.example.ngajiq.ui.main.iqra.components.LetterBox
import com.example.ngajiq.ui.main.iqra.components.ProgressBar
import com.example.ngajiq.ui.theme.*
import com.example.ngajiq.R
import com.example.ngajiq.ui.main.iqra.components.HintTextWithSpeaker
import com.example.ngajiq.ui.main.iqra.components.JawabanBenar
import com.example.ngajiq.ui.main.iqra.components.LetterState

@Composable
fun AlifJawabScreen(navController: NavController) {

    var selectedLetter by remember { mutableStateOf<String?>(null) }
    val jawabanBenar = "I"

    var showJawabanBenar by remember { mutableStateOf(false) }

    // STATE → tombol Lanjut aktif kalau audio sudah dipencet
    var isAudioPlayed by remember { mutableStateOf(true) } // sementara true biar bisa test

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Progress Bar
        ProgressBar(
            progress = 0.2f,
            onPauseClick = { }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Hint Text
        HintTextWithSpeaker(
            text = "Huruf apakah ini?",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Huruf utama
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(200.dp),
        ) {
            DashedLetterBox(
                imageRes = R.drawable.i,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🔵 PILIHAN JAWABAN
        LetterAnswer(
            letters = listOf("A", "I", "U"),
            selectedLetter = selectedLetter,
            jawabanBenar = jawabanBenar,
            onSelect = { selectedLetter = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        // 🔵 Tombol PERIKSA JAWABAN
        ButtonLanjut(
            text = "PERIKSA JAWABAN",
            onClick = {
                if (selectedLetter != null) {
                    // cek jawaban
                    showJawabanBenar = (selectedLetter == jawabanBenar)
                }
            },
            buttonColor = PrimaryBlue,
            shadowColor = DeepBlue,
            textColor = IceBlue,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
        )

        // 🔵 POPUP JAWABAN BENAR
        if (showJawabanBenar) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)),
                contentAlignment = Alignment.Center
            ) {
                JawabanBenar(
                    onLanjutClick = {
                        showJawabanBenar = false
                        navController.navigate("halaman_selanjutnya")
                    },
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }
        }
    }
}


@Composable
fun LetterAnswer(
    letters: List<String>,
    selectedLetter: String?,
    jawabanBenar: String,
    onSelect: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        letters.forEach { letter ->

            val state =
                if (selectedLetter == null) LetterState.Normal
                else if (letter == jawabanBenar && selectedLetter == letter) LetterState.Correct
                else if (selectedLetter == letter) LetterState.Wrong
                else LetterState.Normal

            LetterBox(
                letter = letter,
                state = state,
                enabled = true,
                onClick = { onSelect(letter) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAlifJawabScreen() {
    NgajiQTheme {
        AlifJawabScreen(navController = rememberNavController())
    }
}

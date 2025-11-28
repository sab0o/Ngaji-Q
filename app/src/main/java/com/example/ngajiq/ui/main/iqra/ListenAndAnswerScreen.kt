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
import com.example.ngajiq.ui.main.iqra.components.*
import com.example.ngajiq.ui.theme.*
import com.example.ngajiq.R
import com.example.ngajiq.ui.navigation.Routes

@Composable
fun AlifJawabScreen(navController: NavController) {

    var selectedLetter by remember { mutableStateOf<String?>(null) }
    val jawabanBenar = "I"

    var showJawabanBenar by remember { mutableStateOf(false) }
    var showJawabanSalah by remember { mutableStateOf(false) }

    // Status apakah tombol PERIKSA sudah ditekan
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProgressBar(progress = 0.2f, onPauseClick = {})

        Spacer(modifier = Modifier.height(32.dp))

        HintTextWithSpeaker(
            text = "Huruf apakah ini?",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Gambar huruf utama
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
            isChecked = isChecked,
            onSelect = {
                selectedLetter = it
                isChecked = false // reset warna
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        // 🔵 TOMBOL PERIKSA
        if (!isChecked) {
            ButtonBlue(
                text = "PERIKSA JAWABAN",
                onClick = {
                    if (selectedLetter != null) {
                        isChecked = true
                        if (selectedLetter == jawabanBenar) showJawabanBenar = true
                        else showJawabanSalah = true
                    }
                },
                buttonColor = PrimaryBlue,
                shadowColor = DeepBlue,
                textColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
            )
        }


        // 🟢 POPUP JAWABAN BENAR
        if (showJawabanBenar) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                JawabanBenar(
                    onLanjutClick = {
                        showJawabanBenar = false
                        navController.navigate(Routes.FLASHCARD)
                    },
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }
        }


        // 🔴 POPUP JAWABAN SALAH
        if (showJawabanSalah) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                JawabanSalah(
                    onLanjutClick = {
                        showJawabanSalah = false
                        selectedLetter = null
                        isChecked = false
                    },
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }
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

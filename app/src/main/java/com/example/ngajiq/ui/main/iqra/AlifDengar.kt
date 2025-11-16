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
import com.example.ngajiq.ui.main.iqra.components.HintText
import com.example.ngajiq.ui.main.iqra.components.LetterBox
import com.example.ngajiq.ui.main.iqra.components.SpeakerButton
import com.example.ngajiq.ui.main.iqra.components.ProgressBar
import com.example.ngajiq.ui.theme.*
import com.example.ngajiq.R

@Composable
fun AlifDengarScreen(navController: NavController) {

    // STATE → tombol Lanjut aktif kalau audio sudah dipencet
    var isAudioPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // sesuai theme
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔵 Progress Bar
        ProgressBar(
            progress = 0.2f,
            onPauseClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔵 Hint Text
        HintText(
            text = "Dengarkan bunyi hurufnya!",
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔵 Huruf besar + tombol speaker dalam Box (overlap)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(200.dp),      // bisa disesuaikan
        ) {

            // Kotak huruf di tengah
            DashedLetterBox(
                imageRes = R.drawable.alif,
                modifier = Modifier
                    .fillMaxWidth(0.7f)   // biar kotaknya tidak terlalu besar
                    .align(Alignment.Center)
            )

            // Speaker tombol di pojok kanan atas, overlap
            SpeakerButton(
                onSpeakerClick = { isAudioPlayed = true },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = 10.dp)
            )
        }



        Spacer(modifier = Modifier.height(32.dp))

        // 🔵 EJAAN
        LetterSpelling(letters = listOf("A", "L", "I", "F"))

        Spacer(modifier = Modifier.weight(1f))

        // 🔵 Tombol LANJUT
        ButtonLanjut(
            text = "LANJUT",
            onClick = {
                if (isAudioPlayed) {
                    navController.navigate("alif_baca_jawab")
                }
            },
            buttonColor = if (isAudioPlayed) PrimaryBlue else Color(0xFFE5E7EB),
            shadowColor = if (isAudioPlayed) DeepBlue else Color(0xFFB0BEC5),
            textColor = if (isAudioPlayed) IceBlue else Color(0xFF9CA3AF),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
        )
    }
}

@Composable
fun LetterSpelling(
    letters: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        letters.forEach { letter ->
            LetterBox(
                letter = letter,
                enabled = false   // ⬅ tidak bisa ditekan
            )
            // ⬅ Pake komponen yang sudah kamu punya
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlifDengarScreen() {
    NgajiQTheme {
        AlifDengarScreen(navController = rememberNavController())
    }
}

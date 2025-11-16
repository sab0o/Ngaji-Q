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

@Composable
fun ListenHarakatScreen(navController: NavController) {

    // STATE untuk speaker masing-masing huruf
    var playedA by remember { mutableStateOf(false) }
    var playedI by remember { mutableStateOf(false) }
    var playedU by remember { mutableStateOf(false) }

    val allPlayed = playedA && playedI && playedU

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔵 Progress bar
        ProgressBar(
            progress = 0.2f,
            onPauseClick = {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔵 Hint text
        HintText(
            text = "Dengarkan bunyi hurufnya!",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ======================
        //   BARIS ATAS (A & I)
        // ======================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // ----- HURUF A -----
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(170.dp)
                ) {
                    DashedLetterBox(
                        imageRes = R.drawable.a,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    SpeakerButton(
                        onSpeakerClick = { playedA = true },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-10).dp, y = 10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Huruf A di bawah gambar A
                LetterBox(letter = "A", enabled = false)
            }

            // ----- HURUF I -----
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(170.dp)
                ) {
                    DashedLetterBox(
                        imageRes = R.drawable.i,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    SpeakerButton(
                        onSpeakerClick = { playedI = true },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-10).dp, y = 10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Huruf I di bawah gambar I
                LetterBox(letter = "I", enabled = false)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // ======================
        //   BARIS BAWAH (U)
        // ======================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(170.dp)
                ) {
                    DashedLetterBox(
                        imageRes = R.drawable.u,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    SpeakerButton(
                        onSpeakerClick = { playedU = true },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-10).dp, y = 10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Huruf U di bawah gambar U
                LetterBox(letter = "U", enabled = false)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // ============================
        // 🔵 Tombol LANJUT
        // ============================
        ButtonLanjut(
            text = "LANJUT",
            onClick = {
                if (allPlayed) {
                    navController.navigate("alif_baca_jawab")
                }
            },
            buttonColor = if (allPlayed) PrimaryBlue else Color(0xFFE5E7EB),
            shadowColor = if (allPlayed) DeepBlue else Color(0xFFB0BEC5),
            textColor = if (allPlayed) IceBlue else Color(0xFF9CA3AF),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListenHarakatScreen() {
    NgajiQTheme {
        ListenHarakatScreen(navController = rememberNavController())
    }
}

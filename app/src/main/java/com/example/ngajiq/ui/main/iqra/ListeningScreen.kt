package com.example.ngajiq.ui.main.iqra

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.ngajiq.data.model.HurufHijaiyah
import com.example.ngajiq.data.repository.HurufHijaiyahRepository
import com.example.ngajiq.ui.navigation.Routes

@Composable
fun ListeningScreen(item: HurufHijaiyah, navController: NavController) {

    // STATE
    var isAudioPlayed by remember { mutableStateOf(false) }

    // 🎵 MEDIA PLAYER (remember + cleanup)
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, item.audioRes)   // gunakan audioRes dari item
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release() // cleanup ketika screen keluar
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProgressBar(
            progress = 0.2f,
            onPauseClick = { }
        )

        Spacer(modifier = Modifier.height(32.dp))

        HintText(
            text = "Dengarkan bunyi hurufnya!",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(200.dp)
        ) {

            DashedLetterBox(
                imageRes = item.imageRes,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.Center)
            )

            SpeakerButton(
                onSpeakerClick = {
                    mediaPlayer.start()   // 🔊 PLAY AUDIO
                    isAudioPlayed = true
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        LetterSpelling(
            letters = item.label.toCharArray().map { it.toString() }
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonLanjut(
            text = "LANJUT",
            onClick = {
                if (isAudioPlayed) navController.navigate("writingIqra/${item.id}")

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
fun PreviewListeningScreen() {
    NgajiQTheme {
        ListeningScreen(HurufHijaiyahRepository.getIqra1List()[0],navController = rememberNavController())
    }
}

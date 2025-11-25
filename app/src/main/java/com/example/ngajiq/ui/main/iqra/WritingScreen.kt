package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ngajiq.data.model.HurufHijaiyah
import com.example.ngajiq.ui.main.iqra.components.*
import com.example.ngajiq.ui.theme.DeepBlue
import com.example.ngajiq.ui.theme.IceBlue
import com.example.ngajiq.ui.theme.PrimaryBlue
import com.example.ngajiq.ui.navigation.Routes

@Composable
fun WritingScreen(
    huruf: HurufHijaiyah,
    iqraId: Int,
    navController: NavHostController,
    onPauseClick: () -> Unit = {},
    onSpeakerClick: () -> Unit = {}
) {

    // Knob position DALAM PX, bukan dp!
    var knobPosPx by remember { mutableStateOf(0f) }

    val trackHeightDp = 300.dp
    val knobSizeDp = 80.dp

    // Konversi dp → px agar sama dengan dragAmount.y
    val density = LocalDensity.current
    val trackHeightPx = with(density) { trackHeightDp.toPx() }
    val knobSizePx = with(density) { knobSizeDp.toPx() }
    val maxPositionPx = trackHeightPx - knobSizePx

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProgressBar(progress = 0.45f, onPauseClick = onPauseClick)

        Spacer(Modifier.height(24.dp))

        HintTextWithSpeaker(
            text = "Yuk tulis hurufnya!",
            onClick = onSpeakerClick
        )

        Spacer(Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .height(trackHeightDp)
                .width(140.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            // Track
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .border(3.dp, Color(0xFF55D5FF))
            )

            // Progress fill
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(with(density) { (knobPosPx + knobSizePx).toDp() })
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF5FD9FF))
            )

            // Knob — posisi pakai PX yang diubah ke DP di sini
            WritingKnob(
                knobPositionDp = with(density) { knobPosPx.toDp() },
                onDrag = { dragY ->
                    knobPosPx = (knobPosPx + dragY)
                        .coerceIn(0f, maxPositionPx)
                },
                knobSize = knobSizeDp
            )
        }

        Spacer(Modifier.height(40.dp))

        val selesai = knobPosPx >= maxPositionPx * 0.95f

        ButtonLanjut(
            text = "Lanjut",
            onClick = {
                if (selesai) {
                    navController.navigate(
                        "${Routes.LISTENINGIQRA}/${iqraId}/${huruf.id}"
                    )
                }
            },
            buttonColor = if (selesai) PrimaryBlue else Color(0xFFE5E7EB),
            shadowColor = if (selesai) DeepBlue else Color(0xFFB0BEC5),
            textColor = if (selesai) IceBlue else Color(0xFF9CA3AF),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWritingSlider() {
    WritingScreen(
        huruf = TODO(),
        navController = TODO(),
        onPauseClick = TODO(),
        onSpeakerClick = TODO(),
        iqraId = TODO()
    )
}

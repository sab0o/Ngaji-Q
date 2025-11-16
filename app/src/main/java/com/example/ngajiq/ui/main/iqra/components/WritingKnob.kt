package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ngajiq.R
import com.example.ngajiq.ui.theme.AquaBlue
import com.example.ngajiq.ui.theme.IceBlue

@Composable
fun WritingKnob(
    knobPositionDp: Dp,              // posisi Y knob dalam dp
    knobSize: Dp,                    // diameter luar
    onDrag: (dragY: Float) -> Unit,  // perubahan Y dalam pixel
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .offset(y = knobPositionDp) // diposisikan sesuai DP dari WritingScreen
            .size(knobSize)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    // dragAmount.y = PX
                    onDrag(dragAmount.y)
                }
            },
        contentAlignment = Alignment.Center
    ) {

        // OUTER CIRCLE (Ice Blue 50%)
        Box(
            modifier = Modifier
                .size(knobSize)
                .clip(CircleShape)
                .background(IceBlue.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {

            // INNER CIRCLE (Aqua Blue)
            Box(
                modifier = Modifier
                    .size(knobSize * 0.7f)
                    .clip(CircleShape)
                    .background(AquaBlue),
                contentAlignment = Alignment.Center
            ) {

                // ARROW ICON (arrowdown.png)
                Image(
                    painter = painterResource(id = R.drawable.chevron_down),
                    contentDescription = "Arrow Down",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

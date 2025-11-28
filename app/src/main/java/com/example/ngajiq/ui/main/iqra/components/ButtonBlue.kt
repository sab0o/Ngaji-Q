// File: app/src/main/java/com/example/ngajiq/ui/main/iqra/components/ButtonBlue.kt

package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonBlue(
    text: String,
    onClick: () -> Unit,
    buttonColor: Color,
    shadowColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    buttonHeight: Dp = 53.dp,
    cornerRadius: Dp = 20.dp,
    fontSize: Int = 18
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val buttonOffset = if (isPressed) 2.dp else 6.dp

    Box(
        // modifier yang di-pass dari luar sekarang mengontrol ukuran Box terluar
        modifier = modifier
            .padding(top = 2.dp)
            .height(buttonHeight + buttonOffset),
        contentAlignment = Alignment.TopCenter
    ) {
        // HAPUS .fillMaxWidth() dari sini
        Spacer(
            modifier = Modifier
                .fillMaxWidth() // <-- BIARKAN INI AGAR SHADOW MENGIKUTI LEBAR
                .height(buttonHeight)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(cornerRadius))
                .background(shadowColor)
        )

        // HAPUS .fillMaxWidth() dari sini juga
        Box(
            modifier = Modifier
                .fillMaxWidth() // <-- BIARKAN INI AGAR TOMBOL UTAMA MENGIKUTI LEBAR
                .height(buttonHeight)
                .offset(y = (-buttonOffset))
                .clip(RoundedCornerShape(cornerRadius))
                .background(buttonColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.uppercase(),
                color = textColor,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}